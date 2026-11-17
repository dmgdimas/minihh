package com.dmgdimas.minihh.controller;

import com.dmgdimas.minihh.model.Vacancy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final List<Vacancy> vacancies = List.of(
        new Vacancy(1L, "Java Developer", "hh.ru", new BigDecimal("150000")),
        new Vacancy(2L, "Backend Developer", "Яндекс", new BigDecimal("250000")),
        new Vacancy(3L, "Spring Intern", "Тинькофф", new BigDecimal("80000"))
    );
    @GetMapping
    public List<Vacancy> vacancies() {
        return vacancies;
    }
    @GetMapping("/{id}")
    public Vacancy getVacancyById(@PathVariable Long id){
        return vacancies.stream()
                .filter(v -> v.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vacancy not found with id: " + id));
    }
}
