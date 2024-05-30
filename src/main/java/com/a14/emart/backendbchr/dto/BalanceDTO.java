package com.a14.emart.backendbchr.DTO;

import java.math.BigDecimal;

public class BalanceDTO {
    private Long userId;
    private BigDecimal nominal;

    public BalanceDTO() {}

    public BalanceDTO(Long userId, BigDecimal nominal) {
        this.userId = userId;
        this.nominal = nominal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }
}