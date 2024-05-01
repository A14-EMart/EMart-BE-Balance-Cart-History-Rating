package com.a14.emart.backendbchr.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        Product product1 = new Product();
        product1.setProductId("11111");
        product1.setProductName("Snack Tok");
        product1.setProductQuantity(10);
        product1.setProductPrice(20000);

        Product product2 = new Product();
        product2.setProductId("22222");
        product2.setProductName("Taro");
        product2.setProductQuantity(15);
        product2.setProductPrice(14000);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(product1, 1));
        cartItems.add(new CartItem(product2, 1));

        shoppingCart = new ShoppingCart(cartItems, "123");
    }
    @Test
    public void testCreateCartEmptyProduct() {
        List<CartItem> emptyCartItems = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new ShoppingCart(emptyCartItems, "123"));
    }

    @Test
    public void testAddProductToCart() {
        // Create a new product to add
        Product product3 = new Product();
        product3.setProductId("33333");
        product3.setProductName("Choco Bar");
        product3.setProductQuantity(20);
        product3.setProductPrice(15000);

        shoppingCart.addProduct(product3, 1);

        assertEquals(3, shoppingCart.getProducts().size());
        assertEquals(product3, shoppingCart.getProducts().get(2));
    }

    @Test
    public void testCreateShoppingCartSuccess() {
        assertEquals("Snack Tok", shoppingCart.getProducts().get(0).getProductName());
        assertEquals("Taro", shoppingCart.getProducts().get(1).getProductName());

        assertEquals("10", shoppingCart.getProducts().get(0).getProductQuantity());
        assertEquals("15", shoppingCart.getProducts().get(1).getProductQuantity());

        assertEquals("20000", shoppingCart.getProducts().get(0).getProductPrice());
        assertEquals("14000", shoppingCart.getProducts().get(1).getProductPrice());

        assertEquals(2, shoppingCart.getProducts().size());
    }
}
