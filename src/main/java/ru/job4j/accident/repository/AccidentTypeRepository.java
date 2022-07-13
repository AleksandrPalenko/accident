package ru.job4j.accident.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {

    @NotNull
    List<AccidentType> findAll();
}
