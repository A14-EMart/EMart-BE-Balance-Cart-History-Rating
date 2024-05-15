package com.a14.emart.backendbchr.service;

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
    public BigDecimal getBalance(UUID userId) {
        Balance balance = balanceRepository.findByUserId(userId);
        return balance != null ? balance.getNominal() : BigDecimal.ZERO;
    }

    @Override
    @Transactional
    public void adjustBalance(UUID userId, BigDecimal amount) {
        Balance balance = balanceRepository.findByUserId(userId);
        if (balance != null) {
            balance.setNominal(balance.getNominal().add(amount));
            balanceRepository.save(balance);
        }
    }

    @Override
    @Transactional
    public void withdraw(UUID userId, BigDecimal amount) {
        Balance balance = balanceRepository.findByUserId(userId);
        if (balance != null) {
            BigDecimal newBalance = balance.getNominal().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
                balance.setNominal(newBalance);
                balanceRepository.save(balance);
            } else {
                throw new RuntimeException("Insufficient balance for withdrawal");
            }
        } else {
            throw new RuntimeException("Balance not found for user: " + userId);
        }
    }

    @Override
    @Transactional
    public void topUp(UUID userId, BigDecimal amount) {
        Balance balance = balanceRepository.findByUserId(userId);
        if (balance != null) {
            balance.setNominal(balance.getNominal().add(amount));
            balanceRepository.save(balance);
        } else {
            throw new RuntimeException("Balance not found for user: " + userId);
        }
    }
}
