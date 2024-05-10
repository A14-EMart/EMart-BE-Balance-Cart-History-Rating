package com.a14.emart.backendbchr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class ProductInTransactionTest {
    final Product SAMPLE_PRODUCT = new Product(UUID.randomUUID(), "Mug Doraemon", 30_000L);

    @Test
    void testProductInTransactionDefaultStatus() {
        ProductInTransaction productIT1 = new ProductInTransaction(
                SAMPLE_PRODUCT.getId(), SAMPLE_PRODUCT.getName(), SAMPLE_PRODUCT.getPrice(), 5
        );

        assertEquals(SAMPLE_PRODUCT.getId(), productIT1.getId());
        assertEquals(SAMPLE_PRODUCT.getName(), productIT1.getName());
        assertEquals(SAMPLE_PRODUCT.getPrice(), productIT1.getPrice());
        assertEquals(5, productIT1.getQuantity());
    }

    @Test
    void testLessThanOrEqualZeroQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductInTransaction(SAMPLE_PRODUCT.getId(), SAMPLE_PRODUCT.getName(), SAMPLE_PRODUCT.getPrice(), 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductInTransaction(SAMPLE_PRODUCT.getId(), SAMPLE_PRODUCT.getName(), SAMPLE_PRODUCT.getPrice(), -10);
        });
    }
}
