package com.a14.emart.backendbchr.model;
import com.a14.emart.backendbchr.model.Product;
import lombok.Getter;

@Getter
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

interface CartObserver {
    void update(ShoppingCart cart);
}