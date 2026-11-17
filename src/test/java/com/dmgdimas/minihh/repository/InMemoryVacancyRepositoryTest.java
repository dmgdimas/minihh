package com.dmgdimas.minihh.repository;

import com.dmgdimas.minihh.model.Vacancy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class InMemoryVacancyRepositoryTest {
    private Vacancy VAC1 = new Vacancy(1L, "Java dev", "hh.ru", new BigDecimal("130000"));
    private Vacancy VAC2 = new Vacancy(2L, "C++ dev", "Yandex.ru", new BigDecimal("270000"));

    @Test
    @DisplayName("findAll возвращает все сохранённые вакансии")
    void findAll_returnsAll() {
        var repo = new InMemoryVacancyRepository(List.of(VAC1, VAC2));
        assertThat(repo.findAll()).containsExactly(VAC1, VAC2);
    }

    @Test
    @DisplayName("findById с существующим id возвращает Optional с вакансией")
    void findById_existing_returnsPresent() {
        var repo = new InMemoryVacancyRepository(List.of(V1, V2));
        Optional<Vacancy> found = repo.findById(2L);
        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(V2);
    }

    @Test
    @DisplayName("findById с несуществующим id возвращает пустой Optional")
    void findById_missing_returnsEmpty() {
        var repo = new InMemoryVacancyRepository(List.of(V1));
        assertThat(repo.findById(99L)).isEmpty();
    }
}
