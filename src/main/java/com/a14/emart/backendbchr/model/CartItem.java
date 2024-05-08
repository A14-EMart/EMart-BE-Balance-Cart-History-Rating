package com.a14.emart.backendbchr.model;
import com.a14.emart.backendbchr.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}