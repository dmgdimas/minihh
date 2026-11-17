package com.dmgdimas.minihh.model;

import java.math.BigDecimal;

public record Vacancy(
        Long id,
        String title,
        String company,
        BigDecimal salary
) {
}
