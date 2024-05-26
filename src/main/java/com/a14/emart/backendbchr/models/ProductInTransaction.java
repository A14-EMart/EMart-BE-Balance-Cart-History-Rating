package com.a14.emart.backendbchr.models;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
@Builder
public class ProductInTransaction {
    String id;
    String name;
    Long price;
    int quantity;

    public ProductInTransaction(String id, String name, Long price, int quantity) {
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

    public Long getTotalPrice() {
        return this.price * this.quantity;
    }
}