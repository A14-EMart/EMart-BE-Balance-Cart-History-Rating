package com.a14.emart.backendbchr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pembeli {
    @Id
    @GeneratedValue
    UUID id;

    String nama;

    @OneToMany(mappedBy = "pembeli")
    List<Transaction> transactions;
}
