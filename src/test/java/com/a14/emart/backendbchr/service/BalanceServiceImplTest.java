package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.models.Balance;
import com.a14.emart.backendbchr.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    public void testAdjustBalance() {
        // Given
        UUID userId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("100.00");
        Balance balance = new Balance(userId, initialAmount);
        when(balanceRepository.findByUserId(userId)).thenReturn(balance);

        // When
        BigDecimal adjustmentAmount = new BigDecimal("50.00");
        balanceService.adjustBalance(userId, adjustmentAmount);

        // Then
        assertEquals(initialAmount.add(adjustmentAmount), balance.getNominal());
        Mockito.verify(balanceRepository).save(balance);
    }

    @Test
    public void testWithdrawSufficientBalance() {
        // Given
        UUID userId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("100.00");
        Balance balance = new Balance(userId, initialAmount);
        when(balanceRepository.findByUserId(userId)).thenReturn(balance);

        // When
        BigDecimal withdrawalAmount = new BigDecimal("50.00");
        balanceService.withdraw(userId, withdrawalAmount);

        // Then
        assertEquals(initialAmount.subtract(withdrawalAmount), balance.getNominal());
        Mockito.verify(balanceRepository).save(balance);
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        // Given
        UUID userId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("30.00");
        Balance balance = new Balance(userId, initialAmount);
        when(balanceRepository.findByUserId(userId)).thenReturn(balance);

        // When / Then
        BigDecimal withdrawalAmount = new BigDecimal("50.00");
        assertThrows(RuntimeException.class, () -> balanceService.withdraw(userId, withdrawalAmount));
        assertEquals(initialAmount, balance.getNominal()); // Balance should remain unchanged
        Mockito.verify(balanceRepository, Mockito.never()).save(any());
    }

    @Test
    public void testTopUp() {
        // Given
        UUID userId = UUID.randomUUID();
        BigDecimal initialAmount = new BigDecimal("100.00");
        Balance balance = new Balance(userId, initialAmount);
        when(balanceRepository.findByUserId(userId)).thenReturn(balance);

        // When
        BigDecimal topUpAmount = new BigDecimal("50.00");
        balanceService.topUp(userId, topUpAmount);

        // Then
        assertEquals(initialAmount.add(topUpAmount), balance.getNominal());
        Mockito.verify(balanceRepository).save(balance);
    }
}

