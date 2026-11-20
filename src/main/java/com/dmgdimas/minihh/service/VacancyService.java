package com.dmgdimas.minihh.service;

import com.dmgdimas.minihh.exception.VacancyNotFoundException;
import com.dmgdimas.minihh.model.Vacancy;
import com.dmgdimas.minihh.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class VacancyService {
    private final VacancyRepository repository;
    private final BigDecimal RICH_SALARY_THRESHOLD = new BigDecimal("100000");

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

    public List<String> findRichVacancyTitles() {
        return repository.findAll().stream()
                .filter(v -> v.salary().compareTo(RICH_SALARY_THRESHOLD) > 0)
                .sorted(Comparator.comparing(Vacancy::title))
                .map(Vacancy::title)
                .toList();
    }
}
