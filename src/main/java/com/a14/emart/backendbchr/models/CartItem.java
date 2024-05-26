package com.a14.emart.backendbchr.models;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@Getter
@Setter
@Table(name = "cartItem")
@Entity
public class CartItem {

    @Column(name = "pembeliId")
    private Long pembeliId;

    @Id
    @Column(name = "productId")
    private String productId;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id", nullable = false)
    @JsonIgnoreProperties(value = {"cartItem", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @JsonBackReference
    private ShoppingCart shoppingCart;

    public static CartItemBuilder getBuilder(){
        return new CartItemBuilder();
    }
}