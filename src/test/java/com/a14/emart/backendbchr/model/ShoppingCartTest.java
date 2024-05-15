package com.a14.emart.backendbchr.model;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart = new ShoppingCart();
    private Product product1;
    private Product product2;
    List<Product> products = new ArrayList<>();

    Map<Product, Integer> productQuantities = new HashMap<>();

    @BeforeEach
    public void setUp() {
        Product product1 = new Product();
        product1.setProductId("11111");
        product1.setProductName("Snack Tok");
        product1.setProductQuantity(10);
        product1.setProductPrice(20000.00);

        Product product2 = new Product();
        product2.setProductId("22222");
        product2.setProductName("Taro");
        product2.setProductQuantity(15);
        product2.setProductPrice(14000.00);
        products.add(product1);
        products.add(product2);

        productQuantities.put(product1, 1);
        productQuantities.put(product2, 1);


        shoppingCart.addProduct(product1, 1);
        shoppingCart.addProduct(product2, 1);
    }
    @Test
    public void testCreateCartEmptyProduct() {
        Map<Product, Integer> emptyProducts = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> new ShoppingCart(emptyProducts, "123"));
    }

    @Test
    public void testAddProductToCart() {
        // Create a new product to add
        Product product3 = new Product();
        product3.setProductId("33333");
        product3.setProductName("Choco Bar");
        product3.setProductQuantity(20);
        product3.setProductPrice(15000);
        products.add(product3);
        shoppingCart.addProduct(product3, 1);

        assertEquals(3, shoppingCart.getProductQuantities().size());
        assertEquals(product3.getProductName(), products.get(2).getProductName());
    }

    @Test
    public void testCreateShoppingCartSuccess() {
        assertEquals(2, shoppingCart.getProductQuantities().size());
        assertEquals(1, shoppingCart.getProductQuantities().get(products.get(1)));

    }
}
