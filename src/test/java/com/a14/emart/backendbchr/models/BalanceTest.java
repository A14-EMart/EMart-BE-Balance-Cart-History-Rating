package com.a14.emart.backendbchr.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class BalanceTest {
    private Balance balance;

    @BeforeEach
    public void setUp() {
        this.balance = new Balance();
        this.balance.setUserId("4E4E");
        this.balance.setNominal(20000);
    }

    @Test
    void testGetUserId() {
        assertEquals("4E4E", this.balance.getUserId());
    }

    @Test
    void testGetNominal() {
        assertEquals(20000, this.balance.getNominal());
    }

    @Test
    void testSetNominal() {
        BigDecimal nominalBalance = 10000;
        this.balance.setNominal(nominalBalance);
        assertEquals(nominalBalance, this.balance.getNominal());
    }
}
