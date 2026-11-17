package com.dmgdimas.minihh.service;

import com.dmgdimas.minihh.exception.VacancyNotFoundException;
import com.dmgdimas.minihh.model.Vacancy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VacancyServiceTest {

    private static final Vacancy VACANCY_1 = new Vacancy(1L, "Java dev", "hh.ru", new BigDecimal("150000"));
    private static final Vacancy VACANCY_2 = new Vacancy(2L, "Backend dev", "Yandex", new BigDecimal("300000"));

    @Test
    @DisplayName("findAll возвращает все переданные вакансии")
    void findAll_returnsAllVacancies() {
        // given
        VacancyService service = new VacancyService(List.of(VACANCY_1, VACANCY_2));

        // when
        List<Vacancy> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(VACANCY_1, VACANCY_2);
    }

    @Test
    @DisplayName("findById с существующим id возвращает вакансию")
    void findById_existingId_returnsVacancy() {
        // given
        VacancyService service = new VacancyService(List.of(VACANCY_1, VACANCY_2));

        // when
        Vacancy found = service.findById(2L);

        // then
        assertThat(found).isEqualTo(VACANCY_2);
    }

    @Test
    @DisplayName("findById с несуществующим id бросает VacancyNotFoundException")
    void findById_nonExistingId_throwsVacancyNotFoundException() {
        // given
        VacancyService service = new VacancyService(List.of(VACANCY_1));

        // when & then
        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(VacancyNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("findAll на пустом списке возвращает пустой список")
    void findAll_emptyList_returnsEmptyList() {
        // given
        VacancyService service = new VacancyService(List.of());

        // when
        List<Vacancy> result = service.findAll();

        // then
        assertThat(result).isEmpty();
    }
}