package com.dmgdimas.minihh.exception;

public class VacancyNotFoundException extends RuntimeException {
    public VacancyNotFoundException(Long id) {
        super("Vacancy not found with id: " + id);
    }
}
