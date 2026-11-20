package com.dmgdimas.minihh.config;

import com.dmgdimas.minihh.model.Vacancy;
import com.dmgdimas.minihh.repository.InMemoryVacancyRepository;
import com.dmgdimas.minihh.repository.VacancyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class RepositoryConfig {
    @Bean
    VacancyRepository vacancyRepository() {
        return new InMemoryVacancyRepository(List.of(
                new Vacancy(1L, "Java Developer", "hh.ru", new BigDecimal("150000")),
                new Vacancy(2L, "Backend Developer", "Яндекс", new BigDecimal("300000")),
                new Vacancy(3L, "Spring Intern", "Тинькофф", new BigDecimal("80000"))
        ));
    }
}
