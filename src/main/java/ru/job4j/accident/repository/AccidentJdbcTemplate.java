package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/*
@Repository
 */
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident, String[] rIds) {
        String insertSql = "insert into accident (name, text, address, type_id) "
                + "values (?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement statement = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        Integer idx = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        for (String id : rIds) {
            jdbc.update(
                    "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    idx,
                    Integer.parseInt(id)
            );
        }
        return accident;
    }

    public Accident update(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        /*
        Update table rules for accident
         */
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        for (Rule rules : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rules.getId()
            );
        }
        return accident;
    }

    public Accident findByAccidentId(int id) {
        return jdbc.queryForObject("select a.id as id, a.name as name,"
                        + "       a.text as text,"
                        + "       a.address as address,"
                        + "       t.id as typeId, t.name as typeName"
                        + "       from accident a"
                        + "       join accident_type t on t.id = a.type_id where a.id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("typeId"));
                    accidentType.setName(rs.getString("typeName"));
                    accident.setType(accidentType);
                    accident.setRules(Set.copyOf(findIdRulesByAccident(accident.getId())));
                    return accident;
                },
                id);
    }

    public List<Accident> getAll() {
        return jdbc.query("select a.id  id, a.name  name, a.text  text, a.address address,"
                        + " at.id  id, at.name  name from accident a "
                        + " join accident_type at on  a.type_id = at.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    accident.setType(accidentType);
                    accident.setRules(Set.copyOf(findIdRulesByAccident(accident.getId())));
                    return accident;
                });
    }

    public List<Rule> findIdRulesByAccident(int id) {
        return jdbc.query("select r.id as id, r.name as name "
                        + " from accident_rule a join rules r on r.id = a.rule_id "
                        + " where accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    public List<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public List<Rule> findAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select id, name from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }
}