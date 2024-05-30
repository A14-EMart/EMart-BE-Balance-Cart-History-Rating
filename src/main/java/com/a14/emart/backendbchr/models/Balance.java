package com.a14.emart.backendbchr.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="BALANCE")
public class Balance {
    @Id @Column(name = "USERID", nullable = false)
    private Long userId;

    @Column(name = "NOMINAL", nullable = false)
    private BigDecimal nominal;

    public Balance() {
        // Default
    }

    public static BalanceBuilder getBuilder() {
        return new BalanceBuilder();
    }
}
