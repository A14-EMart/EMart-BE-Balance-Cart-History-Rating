package com.a14.emart.backendbchr.models;

import java.util.Stack;
import java.util.UUID;

public class ShoppingCartBuilder {
    private Long pembeliId;
    private String supermarketId;
    public ShoppingCartBuilder setPembeliId(Long pembeliId){
        this.pembeliId = pembeliId;
        return this;
    }

    public ShoppingCartBuilder setSupermarketId(String supermarketId){
        this.supermarketId = supermarketId;
        return this;
    }

    public ShoppingCart build(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPembeliId(this.pembeliId);
        shoppingCart.setSupermaketId(this.supermarketId);
        return shoppingCart;
    }
}