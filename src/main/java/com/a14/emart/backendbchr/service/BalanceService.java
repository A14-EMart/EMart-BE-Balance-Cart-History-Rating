package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.dto.BalanceDTO;
import com.a14.emart.backendbchr.dto.BalanceRequestDTO;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceService {
    BalanceDTO getBalance(UUID userId);
    void adjustBalance(BalanceRequestDTO request);
    void withdraw(BalanceRequestDTO request);
    void topUp(BalanceRequestDTO request);
}
