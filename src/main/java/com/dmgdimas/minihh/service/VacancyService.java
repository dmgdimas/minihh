package com.dmgdimas.minihh.service;

import com.dmgdimas.minihh.exception.VacancyNotFoundException;
import com.dmgdimas.minihh.model.Vacancy;
import com.dmgdimas.minihh.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.List;


public class VacancyService {
    private final VacancyRepository repository;

    public VacancyService(VacancyRepository repository){
        this.repository = repository;
    }

    public List<Vacancy> findAll() {
        return repository.findAll();
    }

    public Vacancy findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException(id));
    }
}
