package com.a14.emart.backendbchr.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.UUID;

public class BalanceTest {
    private Balance balance;
    UUID userId;

    @BeforeEach
    public void setUp() {
        this.userId = UUID.randomUUID();
        BigDecimal nominal = BigDecimal.valueOf(20000);
        this.balance = new Balance(userId, nominal);
    }

    @Test
    void testGetUserId() {
        assertEquals(this.userId, this.balance.getUserId());
    }

    @Test
    void testGetNominal() {
        assertEquals(BigDecimal.valueOf(20000), this.balance.getNominal());
    }

    @Test
    void testSetNominal() {
        BigDecimal nominalBalance = BigDecimal.valueOf(10000);
        this.balance.setNominal(nominalBalance);
        assertEquals(nominalBalance, this.balance.getNominal());
    }
}
