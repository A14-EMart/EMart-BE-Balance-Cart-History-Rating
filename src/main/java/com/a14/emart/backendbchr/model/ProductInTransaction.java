package com.a14.emart.backendbchr.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Embeddable
public class ProductInTransaction {
    UUID id;
    String name;
    Long price;
    int quantity;

    public ProductInTransaction(UUID id, String name, Long price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        quantityValidator(quantity);
        this.quantity = quantity;
    }

    public void quantityValidator(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
    }
}