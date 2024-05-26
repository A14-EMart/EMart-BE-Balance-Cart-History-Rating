package com.a14.emart.backendbchr.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionBuilder {
    private UUID id;
    private String supermarketName;
    private String buyerName;
    private LocalDateTime tanggalDanWaktuPembelian;
    private List<Product> products;
    private float totalHarga;
    private int rating;
    private String komentar;

    public TransactionBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder supermarketName(String supermarketName) {
        this.supermarketName = supermarketName;
        return this;
    }

    public TransactionBuilder buyerName(String buyerName) {
        this.buyerName = buyerName;
        return this;
    }

    public TransactionBuilder tanggalDanWaktuPembelian(LocalDateTime tanggalDanWaktuPembelian) {
        this.tanggalDanWaktuPembelian = tanggalDanWaktuPembelian;
        return this;
    }

    public TransactionBuilder products(List<Product> products) {
        this.products = products;
        return this;
    }

    public TransactionBuilder totalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
        return this;
    }

    public TransactionBuilder rating(int rating) {
        this.rating = rating;
        return this;
    }

    public TransactionBuilder komentar(String komentar) {
        this.komentar = komentar;
        return this;
    }

    public TransactionBuilder reset() {
        this.id = null;
        this.supermarketName = null;
        this.buyerName = null;
        this.tanggalDanWaktuPembelian = null;
        this.products = null;
        this.totalHarga = 0.0f;
        this.rating = 0;
        this.komentar = null;
        return this;
    }
//    public Transaction build() {
//        Transaction transaction = new Transaction();
//        transaction.setId(this.id);
//        transaction.setSupermarketName(this.supermarketName);
//        transaction.setBuyerName(this.buyerName);
//        transaction.setTanggalDanWaktuPembelian(this.tanggalDanWaktuPembelian);
//        transaction.setProducts(this.products);
//        transaction.setTotalHarga(this.totalHarga);
//        transaction.setRating(this.rating);
//        transaction.setKomentar(this.komentar);
//        return transaction;
//    }
}
