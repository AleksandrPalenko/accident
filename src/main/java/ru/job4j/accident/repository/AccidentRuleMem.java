package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentRuleMem {

    private final Map<Integer, Rule> accidentRule = new HashMap<>();

    public AccidentRuleMem() {
        accidentRule.put(1, Rule.of(1, "Статья. 1"));
        accidentRule.put(2, Rule.of(2, "Статья. 2"));
        accidentRule.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Rule> findAllRules() {
        return accidentRule.values();
    }

    public Rule findById(int id) {
        return accidentRule.get(id);
    }
}
