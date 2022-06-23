package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private AccidentMem() {
        accidents.put(1, new Accident(1, "User1", "Desc", "Address1"));
        accidents.put(2, new Accident(2, "User2", "Desc", "Address2"));
        accidents.put(3, new Accident(3, "User3", "Desc", "Address3"));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

}

