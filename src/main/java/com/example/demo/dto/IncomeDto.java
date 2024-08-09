package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * DTO for {@link com.example.demo.model.Income}
 */
@Getter
@Setter
public class IncomeDto {
    private Double amount;
    private String description;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
}