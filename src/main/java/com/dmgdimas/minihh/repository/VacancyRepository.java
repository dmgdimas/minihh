package com.dmgdimas.minihh.repository;

import com.dmgdimas.minihh.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository {
    public List<Vacancy> findAll();
    public Optional<Vacancy> findById(Long id);
}
