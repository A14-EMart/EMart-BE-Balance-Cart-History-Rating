package com.a14.emart.backendbchr.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="BALANCE")
public class Balance {
    @Id @Column(name = "USERID", nullable = false)
    private UUID userId;

    @Column(name = "NOMINAL", nullable = false)
    private BigDecimal nominal;

    public Balance() {
        // Default
    }

    public Balance(UUID userId, BigDecimal nominal) {
        this.userId = userId;
        this.nominal = nominal;
    }
}
