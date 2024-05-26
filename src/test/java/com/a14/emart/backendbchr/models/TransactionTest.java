package com.a14.emart.backendbchr.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction;
    private UUID transactionId;
    private UUID supermarketId;
    private long pembeliId;
    private LocalDateTime dateTime;
    private List<ProductInTransaction> productList;

    @BeforeEach
    void setUp() {
        transactionId = UUID.randomUUID();
        supermarketId = UUID.randomUUID();
        pembeliId = 12345L;
        dateTime = LocalDateTime.now();
        productList = Arrays.asList(
                new ProductInTransaction("1", "Product A", 100L, 2),
                new ProductInTransaction("2", "Product B", 200L, 1)
        );
        transaction = new Transaction();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(transaction);
    }

    @Test
    void testBuilder() {
        Transaction builtTransaction = Transaction.builder()
                .id(transactionId)
                .supermarketId(supermarketId)
                .supermarketName("Supermarket B")
                .pembeliId(pembeliId)
                .buyerName("Buyer B")
                .tanggalDanWaktuPembelian(dateTime)
                .totalHarga(400.0f)
                .products(productList)
                .rating(4)
                .komentar("Great experience")
                .build();

        assertNotNull(builtTransaction);
        assertEquals(transactionId, builtTransaction.getId());
        assertEquals(supermarketId, builtTransaction.getSupermarketId());
        assertEquals("Supermarket B", builtTransaction.getSupermarketName());
        assertEquals(pembeliId, builtTransaction.getPembeliId());
        assertEquals("Buyer B", builtTransaction.getBuyerName());
        assertEquals(dateTime, builtTransaction.getTanggalDanWaktuPembelian());
        assertEquals(400.0f, builtTransaction.getTotalHarga());
        assertEquals(productList, builtTransaction.getProducts());
        assertEquals(4, builtTransaction.getRating());
        assertEquals("Great experience", builtTransaction.getKomentar());
    }

    @Test
    void testSettersAndGetters() {
        transaction.setId(transactionId);
        transaction.setSupermarketId(supermarketId);
        transaction.setSupermarketName("Supermarket C");
        transaction.setPembeliId(pembeliId);
        transaction.setBuyerName("Buyer C");
        transaction.setTanggalDanWaktuPembelian(dateTime);
        transaction.setTotalHarga(500.0f);
        transaction.setProducts(productList);
        transaction.setRating(3);
        transaction.setKomentar("Average service");

        assertEquals(transactionId, transaction.getId());
        assertEquals(supermarketId, transaction.getSupermarketId());
        assertEquals("Supermarket C", transaction.getSupermarketName());
        assertEquals(pembeliId, transaction.getPembeliId());
        assertEquals("Buyer C", transaction.getBuyerName());
        assertEquals(dateTime, transaction.getTanggalDanWaktuPembelian());
        assertEquals(500.0f, transaction.getTotalHarga());
        assertEquals(productList, transaction.getProducts());
        assertEquals(3, transaction.getRating());
        assertEquals("Average service", transaction.getKomentar());
    }
}
