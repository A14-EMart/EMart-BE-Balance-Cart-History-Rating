package com.a14.emart.backendbchr.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class BalanceRequestDTO {
    private UUID userId;
    private BigDecimal amount;

    public BalanceRequestDTO() {}

    public BalanceRequestDTO(UUID userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}