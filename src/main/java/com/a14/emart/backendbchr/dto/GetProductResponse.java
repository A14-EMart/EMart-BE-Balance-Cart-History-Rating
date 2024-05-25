package com.a14.emart.backendbchr.dto;

import java.util.UUID;

public class GetProductResponse {
    public java.util.UUID id;

    public String name;
    public Long price;
    public Integer stock;

    public UUID getId() {
        return id;
    }

    public void setUUID(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}