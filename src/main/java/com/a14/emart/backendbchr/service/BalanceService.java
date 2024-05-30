package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.DTO.BalanceDTO;
import com.a14.emart.backendbchr.DTO.BalanceRequestDTO;
import com.a14.emart.backendbchr.DTO.TransferRequestDTO;

import java.math.BigDecimal;

public interface BalanceService {
    BalanceDTO getBalance(Long userId);
    void adjustBalance(BalanceRequestDTO request);
    void withdraw(BalanceRequestDTO request);
    void topUp(BalanceRequestDTO request);
    void createBalance(Long userId);
    void transferBalance(TransferRequestDTO request);
}
