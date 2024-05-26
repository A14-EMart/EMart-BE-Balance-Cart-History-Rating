package com.a14.emart.backendbchr.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction;;
    private List<ProductInTransaction> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        products.add(new ProductInTransaction(UUID.randomUUID().toString(), "Product 1", 10L, 2));
        products.add(new ProductInTransaction(UUID.randomUUID().toString(), "Product 2", 5L, 1));

        transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setSupermarketName("Supermarket A");
        transaction.setBuyerName("John Doe");
        transaction.setTanggalDanWaktuPembelian(LocalDateTime.now());
        transaction.setTotalHarga(25.0f);
        transaction.setProducts(products);
        transaction.setRating(5);
        transaction.setKomentar("Great service!");
    }

    @Test
    void testConstructorAndGetters() {
        // Verify that the constructor and getters work as expected
        assertEquals("Supermarket A", transaction.getSupermarketName());
        assertEquals("John Doe", transaction.getBuyerName());
        assertNotNull(transaction.getTanggalDanWaktuPembelian());
        assertEquals(25.0f, transaction.getTotalHarga());
        assertEquals(2, transaction.getProducts().size());
        assertEquals(5, transaction.getRating());
        assertEquals("Great service!", transaction.getKomentar());
    }

    @Test
    void testInvalidData() {
        // Test negative total price
        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setTotalHarga(-5.0f);
        });

        // Test invalid rating (outside 1-5 range)
        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setRating(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setRating(6);
        });

        // Test null product list
        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setProducts(null);
        });

        // Test empty product list
        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setProducts(new ArrayList<>());
        });
    }

    @Test
    void testProductInTransactionInvalidQuantity() {
        // Test creating a product with zero or negative quantity
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductInTransaction(UUID.randomUUID().toString(), "Invalid Product", 10L, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductInTransaction(UUID.randomUUID().toString(), "Invalid Product", 10L, -5);
        });
    }
}
