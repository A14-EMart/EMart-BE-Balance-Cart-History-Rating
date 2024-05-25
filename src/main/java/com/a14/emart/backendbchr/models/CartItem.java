package com.a14.emart.backendbchr.models;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@Getter
@Setter
@Table(name = "cartItem")
@Entity
public class CartItem {
    @Id
    @Column(name = "pembeliId")
    private Long pembeliId;

    @Column(name = "productId")
    private String productId;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id", nullable = false)
    private ShoppingCart shoppingCart;

    public static CartItemBuilder getBuilder(){
        return new CartItemBuilder();
    }

//    public CartItem(Product product, int quantity) {
//        this.product = product;
//        this.quantity = quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
}