package com.example.demo.controller;

import com.example.demo.dto.DateRangeDto;
import com.example.demo.dto.DeleteIncomeDto;
import com.example.demo.dto.IncomeDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "Operations related to transactions")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/create")
    @Operation(summary = "Save a new transaction", description = "Creates a new transaction income.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, Invalid Input"),
    })
    public ResponseEntity<?> create(@RequestBody IncomeDto income) {
        incomeService.save(income);
        return ResponseEntity.ok("Created successfully");
    }

    @GetMapping("/findByDate")
    @Operation(summary = "Get transactions", description = "Retrieve all transactions incomes within a specified date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request, Invalid Input"),
    })
    public ResponseEntity<?> findByDate(@RequestBody DateRangeDto dateRangeDto) {

        if(dateRangeDto.getStartDate().isAfter(dateRangeDto.getEndDate())) {
            return ResponseEntity.badRequest().body("Bad Request, start date cannot be after end date");
        }


        if(dateRangeDto.getStartDate().isEqual(dateRangeDto.getEndDate())) {
            return ResponseEntity.badRequest().body("Bad Request, start date cannot be equal");
        }
        return ResponseEntity.ok(incomeService.findByDate(dateRangeDto.getStartDate(), dateRangeDto.getEndDate()));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a transaction", description = "Deletes a transaction by ID and Date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction ID and Date supplied"),
            @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    public ResponseEntity<?> delete(@RequestBody DeleteIncomeDto deleteIncomeDto) {

        try{
            incomeService.delete(deleteIncomeDto.getId(), deleteIncomeDto.getDate());
            return ResponseEntity.ok("Income Deleted successfully");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete income");
        }
    }

}