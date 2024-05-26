package com.a14.emart.backendbchr.Repository;

import com.a14.emart.backendbchr.models.*;
import com.a14.emart.backendbchr.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
class TransactionRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryTest.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TestEntityManager entityManager;

    private List<ProductInTransaction> products;

    private final UUID SAMPLE1_SUPERMARKET_ID = UUID.randomUUID();
    private final UUID SAMPLE2_SUPERMARKET_ID = UUID.randomUUID();

    private final long SAMPLE1_BUYER_ID = 1;

    private Transaction transaction;
    private Transaction transaction2;
    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        products.add(new ProductInTransaction(UUID.randomUUID().toString(), "Product 1", 10L, 2));
        products.add(new ProductInTransaction(UUID.randomUUID().toString(), "Product 2", 5L, 1));

        transaction = new Transaction();
        transaction.setSupermarketName("Supermarket A");
        transaction.setSupermarketId(SAMPLE1_SUPERMARKET_ID);
        transaction.setBuyerName("John Doe");
        transaction.setPembeliId(SAMPLE1_BUYER_ID);
        transaction.setTanggalDanWaktuPembelian(LocalDateTime.now());
        transaction.setTotalHarga(25.0f);
        transaction.setProducts(products);
        transaction.setRating(5);
        transaction.setKomentar("Great service!");

        transaction2 = new Transaction();
        transaction2.setSupermarketName("Supermarket B");
        transaction2.setSupermarketId(SAMPLE2_SUPERMARKET_ID);
        transaction2.setBuyerName("John Doe");
        transaction2.setPembeliId(SAMPLE1_BUYER_ID);
        transaction2.setTanggalDanWaktuPembelian(LocalDateTime.now());
        transaction2.setTotalHarga(50_000f);
        transaction2.setProducts(products);
        transaction2.setRating(3);
        transaction2.setKomentar("Nice service");

        entityManager.persist(transaction);
        entityManager.persist(transaction2);
        entityManager.flush();
        entityManager.clear();
    }


    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
    }

    @Test
    void testFindTransactionById() {
        List<Transaction> buyerTransaction = transactionRepository.findTransactionByPembeliId(SAMPLE1_BUYER_ID);
        assertEquals(2, buyerTransaction.size());
        assertEquals("Supermarket A", buyerTransaction.getFirst().getSupermarketName());
        assertEquals("Supermarket B", buyerTransaction.get(1).getSupermarketName());
    }

    @Test
    void testFindTransactionInDifferentSupermarket() {
        List<Transaction> buyerTransaction1 = transactionRepository.findTransactionByPembeliIdAndSupermarketId(SAMPLE1_BUYER_ID, SAMPLE1_SUPERMARKET_ID);
        List<Transaction> buyerTransaction2 = transactionRepository.findTransactionByPembeliIdAndSupermarketId(SAMPLE1_BUYER_ID, SAMPLE2_SUPERMARKET_ID);

        assertEquals(1, buyerTransaction1.size());
        assertEquals(1, buyerTransaction2.size());
        assertNotEquals(buyerTransaction1.getFirst(), buyerTransaction2.getFirst());
    }

//    @Test
//    void testDeleteBuyerAndCascadeDeleteTransactions() {
//        // Verify initial state
//        UUID buyerId = buyer.getId();
//        List<Transaction> buyerTransactions = transactionRepository.findTransactionByPembeliId(buyerId);
//        assertEquals(2, buyerTransactions.size());
//
//        // Delete the buyer
//        entityManager.remove(entityManager.find(Pembeli.class, buyerId));
//        entityManager.flush();
//        entityManager.clear();
//
//        // Verify that the buyer is deleted
//        Optional<Pembeli> deletedBuyer = Optional.ofNullable(entityManager.find(Pembeli.class, buyerId));
//        assertTrue(deletedBuyer.isEmpty());
//
//        // Verify that the transactions are also deleted
//        List<Transaction> transactionsAfterDeletion = transactionRepository.findTransactionByPembeliId(buyerId);
//        assertEquals(0, transactionsAfterDeletion.size());
//    }

    @Test
    void testDeleteTransaction() {
        List<Transaction> transactions = transactionRepository.findTransactionByPembeliId(SAMPLE1_BUYER_ID);
        Transaction transaction = transactions.get(0);

        transactionRepository.delete(transaction);

        List<Transaction> remainingTransactions = transactionRepository.findTransactionByPembeliId(SAMPLE1_BUYER_ID);
        assertEquals(1, remainingTransactions.size());
        assertFalse(remainingTransactions.contains(transaction));
    }
}
