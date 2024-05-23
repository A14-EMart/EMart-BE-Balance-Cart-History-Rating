package com.a14.emart.backendbchr.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID supermarketId;

    @Column (nullable = false)
    private String supermarketName;

    @Column (nullable = false)
    private String buyerName;

    @Column (nullable = false)
    private LocalDateTime tanggalDanWaktuPembelian;

    @Column (nullable = false)
    private float totalHarga;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProductInTransaction> products;

    @Column (nullable = false)
    private int rating;

    @Column (nullable = false)
    private String komentar;
}
