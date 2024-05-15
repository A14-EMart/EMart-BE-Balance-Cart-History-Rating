package com.a14.emart.backendbchr.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceService {
    BigDecimal getBalance(UUID userId);
    void adjustBalance(UUID userId, BigDecimal amount);
    void withdraw(UUID userId, BigDecimal amount);
    void topUp(UUID userId, BigDecimal amount);
}
