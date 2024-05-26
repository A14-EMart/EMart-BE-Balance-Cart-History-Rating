package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.dto.BalanceDTO;
import com.a14.emart.backendbchr.dto.BalanceRequestDTO;
import com.a14.emart.backendbchr.models.Balance;
import com.a14.emart.backendbchr.repository.BalanceRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public BalanceDTO getBalance(UUID userId) {
        Balance balance = balanceRepository.findByUserId(userId);
        return balance != null ? new BalanceDTO(balance.getUserId(), balance.getNominal()) : new BalanceDTO(userId, BigDecimal.ZERO);
    }

    @Override
    @Transactional
    public void adjustBalance(BalanceRequestDTO request) {
        Balance balance = balanceRepository.findByUserId(request.getUserId());
        if (balance != null) {
            balance.setNominal(balance.getNominal().add(request.getAmount()));
            balanceRepository.save(balance);
        }
    }

    @Override
    @Transactional
    public void withdraw(BalanceRequestDTO request) {
        Balance balance = balanceRepository.findByUserId(request.getUserId());
        if (balance != null) {
            BigDecimal newBalance = balance.getNominal().subtract(request.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                balance.setNominal(newBalance);
                balanceRepository.save(balance);
            } else {
                throw new RuntimeException("Insufficient balance for withdrawal");
            }
        } else {
            throw new RuntimeException("Balance not found for user: " + request.getUserId());
        }
    }

    @Override
    @Transactional
    public void topUp(BalanceRequestDTO request) {
        Balance balance = balanceRepository.findByUserId(request.getUserId());
        if (balance != null) {
            balance.setNominal(balance.getNominal().add(request.getAmount()));
            balanceRepository.save(balance);
        } else {
            throw new RuntimeException("Balance not found for user: " + request.getUserId());
        }
    }
}
