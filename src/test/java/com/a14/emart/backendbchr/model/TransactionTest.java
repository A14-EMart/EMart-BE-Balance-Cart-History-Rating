package com.a14.emart.backendbchr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TransactionTest {
    private List<Transactions> transactions;

    @BeforeEach
    void setUp() {
        this.transactions = new ArrayList<>();
        Supermarket supermarket = new Supermarket();

        Pembeli pembeli1 = new Pembeli();
        List<Product> products1 = new ArrayList<>();

        Pembeli pembeli2 = new Pembeli();
        List<Product> products2 = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setSupermarket(supermarket);
        transaction1.setPembeli(pembeli1);
        transaction1.setTanggalDanWaktuPembelian("13/11/2023");
        transaction1.setProduct(products1);
        transaction1.setTotalHarga(13000);
        transaction1.setRating(5);
        transaction1.setKomentar("Transaction fiktif");

        Transaction transaction2 = new Transaction();
        transaction1.setSupermarket(supermarket);
        transaction1.setPembeli(pembeli2);
        transaction1.setTanggalDanWaktuPembelian("14/09/2023");
        transaction1.setProduct(products2);
        transaction1.setTotalHarga(134000);
        transaction1.setRating(3);
        transaction1.setKomentar("Transaction fiktif juga");
    }

    @Test
    void testTransactionStatus
}
