package com.a14.emart.backendbchr.models;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
    private double productPrice;


    public Product() {
        this.productId = UUID.randomUUID().toString();
    }
}