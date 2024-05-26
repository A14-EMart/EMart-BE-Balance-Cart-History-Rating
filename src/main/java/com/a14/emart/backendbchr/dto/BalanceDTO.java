package com.a14.emart.backendbchr.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class BalanceDTO {
    private UUID userId;
    private BigDecimal nominal;

    public BalanceDTO() {}

    public BalanceDTO(UUID userId, BigDecimal nominal) {
        this.userId = userId;
        this.nominal = nominal;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }
}