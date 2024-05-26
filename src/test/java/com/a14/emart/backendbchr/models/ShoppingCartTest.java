package com.a14.emart.backendbchr.models;

import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.models.ShoppingCartBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    private CartItem item1;
    private CartItem item2;

    @BeforeEach
    void setUp() {
        // Initialize the shopping cart
        shoppingCart = ShoppingCart.getBuilder()
                .setPembeliId(1L)
                .setSupermarketId("supermarket456")
                .build();

        // Initialize cart items
        item1 = CartItem.getBuilder()
                .setPembeliId(1L)
                .setProductId("product789")
                .setAmount(2)
                .build();

        item2 = CartItem.getBuilder()
                .setPembeliId(1L)
                .setProductId("product101")
                .setAmount(1)
                .build();
    }

    @Test
    public void testShoppingCart() {
        Long pembeliId = 1L;
        String supermarketId = UUID.randomUUID().toString();

        ShoppingCart shoppingCart = new ShoppingCartBuilder()
                .setPembeliId(pembeliId)
                .setSupermarketId(supermarketId)
                .build();

        assertEquals(pembeliId, shoppingCart.getPembeliId());
        assertEquals(supermarketId, shoppingCart.getSupermaketId());
    }

    @Test
    void testAddItem() {
        shoppingCart.addItem(item1);

        assertEquals(1, shoppingCart.getItems().size());
        assertTrue(shoppingCart.getItems().contains(item1));
        assertEquals(shoppingCart, item1.getShoppingCart());

        shoppingCart.addItem(item2);

        assertEquals(2, shoppingCart.getItems().size());
        assertTrue(shoppingCart.getItems().contains(item2));
        assertEquals(shoppingCart, item2.getShoppingCart());
    }

    @Test
    void testRemoveItem() {
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);

        shoppingCart.removeItem(item1);

        assertEquals(1, shoppingCart.getItems().size());
        assertFalse(shoppingCart.getItems().contains(item1));
        assertNull(item1.getShoppingCart());

        shoppingCart.removeItem(item2);

        assertEquals(0, shoppingCart.getItems().size());
        assertFalse(shoppingCart.getItems().contains(item2));
        assertNull(item2.getShoppingCart());
    }

}