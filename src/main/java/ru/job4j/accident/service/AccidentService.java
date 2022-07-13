package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentRepository store;
    private final AccidentTypeRepository storeOfType;
    private final RuleRepository storeOfRule;

    @Autowired
    public AccidentService(AccidentRepository store, AccidentTypeRepository storeOfType, RuleRepository storeOfRule) {
        this.store = store;
        this.storeOfType = storeOfType;
        this.storeOfRule = storeOfRule;
    }

    public List<Accident> findAll() {
        return store.findAll();
    }

    public void create(Accident accident) {
        if (accident.getId() == 0) {
            store.save(accident);
        }
    }

    public Accident findByAccidentId(int id) {
        return store.findById(id).get();
    }

    public void update(Accident accident) {
        store.save(accident);
    }

    public Collection<AccidentType> findAllTypes() {
        return storeOfType.findAll();
    }

    public Collection<Rule> findAllRules() {
        return storeOfRule.findAll();
    }

    public Set<Rule> findRulesForAccident(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        if (ids != null) {
            Arrays.stream(ids).forEach(id -> rules.add(storeOfRule.findById(Integer.parseInt(id)).orElse(null)));
        }
        return rules;
    }
}
