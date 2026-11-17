package com.dmgdimas.minihh.service;

import com.dmgdimas.minihh.exception.VacancyNotFoundException;
import com.dmgdimas.minihh.model.Vacancy;

import java.util.ArrayList;
import java.util.List;


public class VacancyService {
    private final List<Vacancy> vacancies;

    public VacancyService(List<Vacancy> vacancies){
        this.vacancies = vacancies;
    }

    public List<Vacancy> findAll() {
        return vacancies;
    }

    public Vacancy findById(Long id){
        return vacancies.stream()
                .filter(v -> v.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new VacancyNotFoundException(id));
    }
}
