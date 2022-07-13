package ru.job4j.accident.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    @NotNull
    List<Rule> findAll();

}
