package com.a14.emart.backendbchr.Service;

import com.a14.emart.backendbchr.DTO.BalanceDTO;
import com.a14.emart.backendbchr.DTO.BalanceRequestDTO;
import com.a14.emart.backendbchr.models.Balance;
import com.a14.emart.backendbchr.repository.BalanceRepository;
import com.a14.emart.backendbchr.service.BalanceService;
import com.a14.emart.backendbchr.service.BalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
public class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    private Long userId;
    private Balance balance;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userId = 1L;
        this.balance = Balance.getBuilder()
                .setUserId(this.userId)
                .setNominal(BigDecimal.valueOf(100))
                .build();
    }

    @Test
    public void testAdjustBalance() {
        BalanceRequestDTO request = new BalanceRequestDTO(this.userId, BigDecimal.valueOf(50));
        when(balanceRepository.findByUserId(this.userId)).thenReturn(this.balance);

        balanceService.adjustBalance(request);

        verify(balanceRepository, times(1)).save(this.balance);
        assertEquals(BigDecimal.valueOf(150), this.balance.getNominal());
    }

    @Test
    public void testWithdrawSufficientBalance() {
        BalanceRequestDTO request = new BalanceRequestDTO(this.userId, BigDecimal.valueOf(50));
        when(balanceRepository.findByUserId(this.userId)).thenReturn(this.balance);

        balanceService.withdraw(request);

        verify(balanceRepository, times(1)).save(this.balance);
        assertEquals(BigDecimal.valueOf(50), this.balance.getNominal());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        BalanceRequestDTO request = new BalanceRequestDTO(this.userId, BigDecimal.valueOf(150));
        when(balanceRepository.findByUserId(this.userId)).thenReturn(this.balance);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> balanceService.withdraw(request));
        assertEquals("Insufficient balance for withdrawal", exception.getMessage());
    }

    @Test
    public void testTopUp() {
        BalanceRequestDTO request = new BalanceRequestDTO(this.userId, BigDecimal.valueOf(50));
        when(balanceRepository.findByUserId(this.userId)).thenReturn(this.balance);

        balanceService.topUp(request);

        verify(balanceRepository, times(1)).save(this.balance);
        assertEquals(BigDecimal.valueOf(150), this.balance.getNominal());
    }
}

