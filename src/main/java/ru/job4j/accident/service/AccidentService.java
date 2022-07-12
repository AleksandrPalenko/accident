package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentHibernate accidentsRep;

    public AccidentService(AccidentHibernate accidentsRep) {
        this.accidentsRep = accidentsRep;
    }

    public Collection<Accident> findAll() {
        return accidentsRep.getAll();
    }

    public void create(Accident accident, String[] rIds) {
        if (accident.getId() == 0) {
            accidentsRep.save(accident, rIds);
        } else {
            accidentsRep.update(accident);
        }

    }

    public Accident findByAccidentId(int id) {
        return accidentsRep.findByAccidentId(id);
    }

    public void update(Accident accident) {
        accidentsRep.update(accident);
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentsRep.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentsRep.findAllRules();
    }

    public Set<Rule> findRulesForAccident(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        if (ids != null) {
            Arrays.stream(ids).forEach(id -> rules.add(accidentsRep.findRuleById(Integer.parseInt(id))));
        }
        return rules;
    }
}
