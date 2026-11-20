package com.dmgdimas.minihh.controller;

import com.dmgdimas.minihh.model.Vacancy;
import com.dmgdimas.minihh.service.VacancyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService){
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public List<Vacancy> getAllVacancies() {
        return vacancyService.findAll();
    }

    @GetMapping("/{id}")
    public Vacancy getVacancyById(@PathVariable Long id){
        return vacancyService.findById(id);
    }

    @GetMapping("/titles")
    public List<String> getRichVacancyTitles(){
        return vacancyService.findRichVacancyTitles();
    }
}
