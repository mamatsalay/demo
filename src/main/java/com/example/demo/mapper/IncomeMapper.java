package com.example.demo.mapper;

import com.example.demo.dto.IncomeDto;
import com.example.demo.model.Income;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncomeMapper {

    public IncomeDto toIncomeDto(Income income) {
        if (income == null) {
            return null;
        }
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setAmount(income.getAmount());
        incomeDto.setDescription(income.getDescription());
        incomeDto.setDate(income.getDate());
        return incomeDto;
    }

    public Income toIncome(IncomeDto incomeDto) {
        if (incomeDto == null) {
            return null;
        }
        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setDescription(incomeDto.getDescription());
        income.setDate(incomeDto.getDate());
        return income;
    }

    public List<IncomeDto> toIncomeDtoList(List<Income> incomeList) {
        if (incomeList == null) {
            return null;
        }

        return incomeList.stream().map(this::toIncomeDto).collect(Collectors.toList());
    }



}
