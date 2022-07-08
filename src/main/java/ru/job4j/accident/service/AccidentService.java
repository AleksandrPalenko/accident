package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentRuleMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentRuleMem accidentRuleMem;

    public AccidentService(AccidentMem accidentMem, AccidentRuleMem accidentRuleMem) {
        this.accidentMem = accidentMem;
        this.accidentRuleMem = accidentRuleMem;
    }

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
         accidentMem.update(accident);
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentMem.findAllType();
    }

    public Collection<Rule> findAllRules() {
        return accidentRuleMem.findAllRules();
    }

    public Rule findByRuleId(int id) {
        return accidentRuleMem.findById(id);
    }
}
