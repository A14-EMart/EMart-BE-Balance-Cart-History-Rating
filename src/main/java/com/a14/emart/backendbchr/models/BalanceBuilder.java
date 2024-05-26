package com.a14.emart.backendbchr.models;

import java.math.BigDecimal;
import java.util.UUID;

public class BalanceBuilder {
    private UUID userId;
    private BigDecimal nominal;

    public BalanceBuilder setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public BalanceBuilder setNominal(BigDecimal nominal) {
        this.nominal = nominal;
        return this;
    }

    public Balance build() {
        Balance balance = new Balance();
        balance.setUserId(this.userId);
        balance.setNominal(this.nominal);
        return balance;
    }
}
