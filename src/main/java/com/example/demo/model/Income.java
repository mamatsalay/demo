package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the transaction", example = "1")
    private Long id;

    @Schema(description = "Amount of the transaction", example = "100.50")
    private Double amount;


    @Schema(description = "Description of the transaction", example = "Salary for July")
    private String description;

    @Schema(description = "Date of the transaction", example = "2024-07-21")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;

}