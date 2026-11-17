package com.dmgdimas.minihh.repository;

import com.dmgdimas.minihh.model.Vacancy;

import java.util.List;
import java.util.Optional;

public class InMemoryVacancyRepository implements VacancyRepository {
    private final List<Vacancy> vacancies;
    public InMemoryVacancyRepository(List<Vacancy> vacancies){
        this.vacancies = vacancies;
    }

    @Override
    public List<Vacancy> findAll() {
        return List.copyOf(vacancies);
    }

    @Override
    public Optional<Vacancy> findById(Long id) {
        return vacancies.stream()
                .filter(v -> v.id().equals(id))
                .findFirst();
    }
}
