package com.a14.emart.backendbchr.dto;

import java.math.BigDecimal;

public class TransferRequestDTO {
    private Long fromUserId;
    private Long toUserId;
    private BigDecimal amount;

    public TransferRequestDTO() {}

    public TransferRequestDTO(Long fromUserId, Long toUserId, BigDecimal amount) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}