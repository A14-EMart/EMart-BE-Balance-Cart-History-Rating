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
import java.util.UUID;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<BalanceDTO>> getBalance(@PathVariable UUID userId) {
        BalanceDTO balance = balanceService.getBalance(userId);
        ApiResponse<BalanceDTO> response = new ApiResponse<>(true, "Balance retrieved successfully", balance);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adjust")
    public ResponseEntity<ApiResponse<String>> adjustBalance(@RequestBody BalanceRequestDTO request) {
        balanceService.adjustBalance(request);
        ApiResponse<String> response = new ApiResponse<>(true, "Transaction successful", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<String>> withdraw(@RequestBody BalanceRequestDTO request) {
        try {
            balanceService.withdraw(request);
            ApiResponse<String> response = new ApiResponse<>(true, "Withdrawal successful", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/top-up")
    public ResponseEntity<ApiResponse<String>> topUp(@RequestBody BalanceRequestDTO request) {
        try {
            balanceService.topUp(request);
            ApiResponse<String> response = new ApiResponse<>(true, "Top-up successful", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
