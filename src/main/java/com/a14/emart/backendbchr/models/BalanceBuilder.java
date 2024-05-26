package com.a14.emart.backendbchr.models;

import java.math.BigDecimal;

public class BalanceBuilder {
    private Long userId;
    private BigDecimal nominal;

    public BalanceBuilder setUserId(Long userId) {
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
