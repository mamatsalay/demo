package com.example.demo.service;

import com.example.demo.dto.IncomeDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.IncomeMapper;
import com.example.demo.model.Income;
import com.example.demo.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, IncomeMapper incomeMapper) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
    }

    public void save(IncomeDto incomeDto) {
         incomeRepository.save(incomeMapper.toIncome(incomeDto));
    }

    public List<IncomeDto> findByDate(LocalDate startDate, LocalDate endDate) {

        List<Income> list = incomeRepository.findByDateBetween(startDate, endDate);

        return incomeMapper.toIncomeDtoList(list);
    }

    public void delete(Long id, LocalDate date) {

        Optional<Income> income = incomeRepository.findByIdAndDate(id, date);

        if (income.isPresent()) {
            incomeRepository.deleteByIdAndDate(id, date);
        } else {
            throw new NotFoundException("Income entry with the given ID and date does not exist.");
        }

    }
}
