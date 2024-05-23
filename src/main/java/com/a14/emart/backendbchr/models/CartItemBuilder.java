package com.a14.emart.backendbchr.models;

public class CartItemBuilder {
    private String pembeliId;
    private String productId;
    private int amount;

    public CartItemBuilder setPembeliId(String pembeliId){
        this.pembeliId = pembeliId;
        return this;
    }

    public CartItemBuilder setProductId(String productId){
        this.productId = productId;
        return  this;
    }

    public CartItemBuilder setAmount(Integer amount){
        this.amount = amount;
        return  this;
    }

    public CartItem build(){
        CartItem cartItem = new CartItem();
        cartItem.setPembeliId(this.pembeliId);
        cartItem.setProductId(this.productId);
        cartItem.setAmount(this.amount);
        return cartItem;
    }
}