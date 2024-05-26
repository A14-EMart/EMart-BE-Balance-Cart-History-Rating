package com.a14.emart.backendbchr.models;

import com.a14.emart.backendbchr.models.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartItemTest {

    @Test
    void testCartItemBuilder() {
        Long expectedPembeliId = 2L;
        String expectedProductId = "product456";
        int expectedAmount = 10;

        CartItem cartItem = CartItem.getBuilder()
                .setProductId(expectedProductId)
                .setAmount(expectedAmount)
                .build();

        assertNotNull(cartItem);
        assertEquals(expectedProductId, cartItem.getProductId());
        assertEquals(expectedAmount, cartItem.getAmount());
    }
}