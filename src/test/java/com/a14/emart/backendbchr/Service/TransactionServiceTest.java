package com.a14.emart.backendbchr.Service;

import com.a14.emart.backendbchr.models.Transaction;
import com.a14.emart.backendbchr.repository.TransactionRepository;
import com.a14.emart.backendbchr.service.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction transaction1;
    private Transaction transaction2;
    private UUID supermarketId1;
    private UUID supermarketId2;
    private long buyerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        supermarketId1 = UUID.randomUUID();
        supermarketId2 = UUID.randomUUID();
        buyerId = 12345L;

        transaction1 = Transaction.builder()
                .id(UUID.randomUUID())
                .supermarketId(supermarketId1)
                .supermarketName("Supermarket A")
                .pembeliId(buyerId)
                .buyerName("Buyer A")
                .tanggalDanWaktuPembelian(LocalDateTime.now())
                .totalHarga(300.0f)
                .products(Arrays.asList())
                .rating(5)
                .komentar("Good service")
                .build();

        transaction2 = Transaction.builder()
                .id(UUID.randomUUID())
                .supermarketId(supermarketId2)
                .supermarketName("Supermarket B")
                .pembeliId(buyerId)
                .buyerName("Buyer A")
                .tanggalDanWaktuPembelian(LocalDateTime.now())
                .totalHarga(450.0f)
                .products(Arrays.asList())
                .rating(4)
                .komentar("Very good service")
                .build();
    }

    @Test
    void testCreate() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction1);

        Transaction createdTransaction = transactionService.create(transaction1);

        assertNotNull(createdTransaction);
        assertEquals(transaction1.getId(), createdTransaction.getId());
        verify(transactionRepository, times(1)).save(transaction1);
    }

    @Test
    void testFindByBuyer() {
        when(transactionRepository.findTransactionByPembeliId(buyerId)).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> transactions = transactionService.findByBuyer(buyerId);

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals(transaction1.getId(), transactions.get(0).getId());
        assertEquals(transaction2.getId(), transactions.get(1).getId());
        verify(transactionRepository, times(1)).findTransactionByPembeliId(buyerId);
    }

    @Test
    void testFindByBuyerPerSupermarket() {
        when(transactionRepository.findTransactionByPembeliIdAndSupermarketId(buyerId, supermarketId1))
                .thenReturn(Arrays.asList(transaction1));

        List<Transaction> transactions = transactionService.findyByBuyerPerSupermarket(buyerId, supermarketId1);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(transaction1.getId(), transactions.get(0).getId());
        verify(transactionRepository, times(1)).findTransactionByPembeliIdAndSupermarketId(buyerId, supermarketId1);
    }

    @Test
    void testFindBySupermarket() {
        when(transactionRepository.findTransactionBySupermarketId(supermarketId1))
                .thenReturn(Arrays.asList(transaction1));

        List<Transaction> transactions1 = transactionService.findBySupermarket(supermarketId1);

        assertNotNull(transactions1);
        assertEquals(1, transactions1.size());
        assertEquals(transaction1.getId(), transactions1.get(0).getId());
        verify(transactionRepository, times(1)).findTransactionBySupermarketId(supermarketId1);

        when(transactionRepository.findTransactionBySupermarketId(supermarketId2))
                .thenReturn(Arrays.asList(transaction2));

        List<Transaction> transactions2 = transactionService.findBySupermarket(supermarketId2);

        assertNotNull(transactions2);
        assertEquals(1, transactions2.size());
        assertEquals(transaction2.getId(), transactions2.get(0).getId());
        verify(transactionRepository, times(1)).findTransactionBySupermarketId(supermarketId2);
    }
}
