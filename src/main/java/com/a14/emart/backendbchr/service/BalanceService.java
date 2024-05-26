package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.dto.BalanceDTO;
import com.a14.emart.backendbchr.dto.BalanceRequestDTO;

import java.math.BigDecimal;

public interface BalanceService {
    BalanceDTO getBalance(Long userId);
    void adjustBalance(BalanceRequestDTO request);
    void withdraw(BalanceRequestDTO request);
    void topUp(BalanceRequestDTO request);
    void createBalance(Long userId);
}
