package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcRepository;

    public AccidentService(AccidentJdbcTemplate accidentJdbcRepository) {
        this.accidentJdbcRepository = accidentJdbcRepository;
    }

    public Collection<Accident> findAll() {
        return accidentJdbcRepository.getAll();
    }

    public void create(Accident accident, String[] rIds) {
        if (accident.getId() == 0) {
            accidentJdbcRepository.save(accident, rIds);
        } else {
            accidentJdbcRepository.update(accident);
        }

    }

    public Accident findByAccidentId(int id) {
        return accidentJdbcRepository.findByAccidentId(id);
    }

    public void update(Accident accident) {
        accidentJdbcRepository.update(accident);
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentJdbcRepository.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentJdbcRepository.findAllRules();
    }

    public Set<Rule> findRulesForAccident(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        if (ids != null) {
            Arrays.stream(ids).forEach(id -> rules.add(accidentJdbcRepository.findRuleById(Integer.parseInt(id))));
        }
        return rules;
    }
}
