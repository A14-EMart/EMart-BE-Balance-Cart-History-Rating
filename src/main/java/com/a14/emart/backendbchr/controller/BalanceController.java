package com.a14.emart.backendbchr.controller;

import com.a14.emart.backendbchr.dto.BalanceDTO;
import com.a14.emart.backendbchr.dto.BalanceRequestDTO;
import com.a14.emart.backendbchr.service.BalanceService;
import com.a14.emart.backendbchr.controller.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<BalanceDTO>> getBalance(@PathVariable Long userId) {
        BalanceDTO balance = balanceService.getBalance(userId);
        ApiResponse<BalanceDTO> response = new ApiResponse<>(true, "Balance retrieved successfully", balance);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adjust")
    public ResponseEntity<ApiResponse<BigDecimal>> adjustBalance(@RequestBody BalanceRequestDTO request) {
        balanceService.adjustBalance(request);
        BalanceDTO balance = balanceService.getBalance(request.getUserId());
        ApiResponse<BigDecimal> response = new ApiResponse<>(true, "Transaction successful", balance.getNominal());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<BigDecimal>> withdraw(@RequestBody BalanceRequestDTO request) {
        try {
            balanceService.withdraw(request);
            BalanceDTO balance = balanceService.getBalance(request.getUserId());
            ApiResponse<BigDecimal> response = new ApiResponse<>(true, "Withdrawal successful", balance.getNominal());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<BigDecimal> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/top-up")
    public ResponseEntity<ApiResponse<BigDecimal>> topUp(@RequestBody BalanceRequestDTO request) {
        try {
            balanceService.topUp(request);
            BalanceDTO balance = balanceService.getBalance(request.getUserId());
            ApiResponse<BigDecimal> response = new ApiResponse<>(true, "Top-up successful", balance.getNominal());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<BigDecimal> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createBalance(@RequestBody BalanceRequestDTO request) {
        try {
            balanceService.createBalance(request.getUserId());
            ApiResponse<String> response = new ApiResponse<>(true, "Balance created successfully", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
