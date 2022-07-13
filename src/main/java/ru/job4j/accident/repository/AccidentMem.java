package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
@Repository
 */
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final AtomicInteger ids = new AtomicInteger(1);

    private final Map<Integer, AccidentType> accidentType = new HashMap<>();

    private AccidentMem() {
        accidentType.put(1, AccidentType.of(1, "Две машины"));
        accidentType.put(2, AccidentType.of(2, "Машина и человек"));
        accidentType.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents.put(1, new Accident(1, "Number1", "Text1", "Address1", accidentType.get(0)));
        accidents.put(2, new Accident(2, "Number2", "Text2", "Address2", accidentType.get(1)));
        accidents.put(3, new Accident(3, "Number3", "Text3", "Address3", accidentType.get(2)));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(ids.get());
        accidents.put(ids.getAndIncrement(), accident);
        accident.setType(accidentType.get(accident.getType().getId()));
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accident.setType(accidentType.get(accident.getType().getId()));
        accidents.replace(accident.getId(), accident);
    }

    public Collection<AccidentType> findAllType() {
        return accidentType.values();
    }

}

