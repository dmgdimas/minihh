package com.dmgdimas.minihh.controller;

import com.dmgdimas.minihh.model.Vacancy;
import com.dmgdimas.minihh.repository.InMemoryVacancyRepository;
import com.dmgdimas.minihh.repository.VacancyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class VacancyControllerIntegrationTest {

    private static final Vacancy TEST_VACANCY_1 =
            new Vacancy(1L, "Test Dev A", "Test Corp", new BigDecimal("100000"));
    private static final Vacancy TEST_VACANCY_2 =
            new Vacancy(2L, "Test Dev B", "Test Corp", new BigDecimal("200000"));

    @TestConfiguration
    static class FixtureConfig {
        @Bean
        @Primary
        VacancyRepository testVacancyRepository() {
            return new InMemoryVacancyRepository(List.of(TEST_VACANCY_1, TEST_VACANCY_2));
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/vacancies возвращает ровно тестовые фикстуры")
    void getAllVacancies_returnsTestFixtures() throws Exception {
        mockMvc.perform(get("/api/vacancies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test Dev A"))
                .andExpect(jsonPath("$[1].title").value("Test Dev B"));
    }

    @Test
    @DisplayName("GET /api/vacancies/{id} возвращает фикстуру по id")
    void getVacancyById_existingId_returnsFixture() throws Exception {
        mockMvc.perform(get("/api/vacancies/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Dev B"))
                .andExpect(jsonPath("$.company").value("Test Corp"));
    }

    @Test
    @DisplayName("GET /api/vacancies/{id} с несуществующим id возвращает problem+json 404")
    void getVacancyById_nonExistingId_returnsProblemDetail() throws Exception {
        mockMvc.perform(get("/api/vacancies/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Vacancy Not Found"))
                .andExpect(jsonPath("$.detail").value("Vacancy not found with id: 99"))
                .andExpect(jsonPath("$.instance").value("/api/vacancies/99"))
                .andExpect(jsonPath("$.errorCode").value("VACANCY_NOT_FOUND"));
    }

    @Test
    @DisplayName("ошибка фреймворка (кривой id) тоже отдаётся как problem+json")
    void frameworkError_returnsProblemDetail() throws Exception {
        mockMvc.perform(get("/api/vacancies/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/problem+json"))
                .andExpect(jsonPath("$.status").value(400));
    }
}