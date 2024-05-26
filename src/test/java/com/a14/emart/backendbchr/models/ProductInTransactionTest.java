package com.a14.emart.backendbchr.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductInTransactionTest {

    private ProductInTransaction product;

    @BeforeEach
    void setUp() {
        product = new ProductInTransaction();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(product);
    }

    @Test
    void testBuilder() {
        ProductInTransaction productWithBuilder = ProductInTransaction.builder()
                .id("2")
                .name("Product B")
                .price(200L)
                .quantity(3)
                .build();
        assertNotNull(productWithBuilder);
        assertEquals("2", productWithBuilder.getId());
        assertEquals("Product B", productWithBuilder.getName());
        assertEquals(200L, productWithBuilder.getPrice());
        assertEquals(3, productWithBuilder.getQuantity());
    }

    @Test
    void testGetTotalPrice() {
        product = new ProductInTransaction("3", "Product C", 150L, 4);
        assertEquals(600L, product.getTotalPrice());
    }

    @Test
    void testSettersAndGetters() {
        product.setId("4");
        product.setName("Product D");
        product.setPrice(250L);
        product.setQuantity(1);

        assertEquals("4", product.getId());
        assertEquals("Product D", product.getName());
        assertEquals(250L, product.getPrice());
        assertEquals(1, product.getQuantity());
    }
}
