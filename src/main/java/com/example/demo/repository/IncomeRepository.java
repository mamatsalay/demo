package com.example.demo.repository;

import com.example.demo.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<Income> findByIdAndDate(long id, LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Income i WHERE i.id = :id AND i.date = :date")
    void deleteByIdAndDate(@Param("id") Long id, @Param("date") LocalDate date);

}