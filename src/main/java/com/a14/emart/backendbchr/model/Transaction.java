package com.a14.emart.backendbchr.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/*
Supermarket memiliki atribut rating.

Pembeli dan Pengelola Supermarket dapat melihat daftar riwayat transaksi yang dilakukan.
Pengelola supermarket hanya dapat melihat daftar riwayat transaksi di Supermarket tersebut.

Pembeli dapat melakukan filter daftar riwayat transaksi berdasarkan supermarket.

Pembeli dan Pengelola Supermarket dapat melihat detail transaksi yang dilakukan.

Detail transaksi memuat nama Supermarket dan Pembeli, tanggal dan waktu pembelian,
daftar produk yang dibeli, total harga, rating dan komentar.

Pembeli dapat memberikan rating dan juga komentar kepada Supermarket di setiap transaksi.
 */
@Getter
@Setter
public class Transaction {
    UUID id;
    Supermarket supermarket;
    Pembeli pembeli;
    String tanggalDanWaktuPembelian;
    List<Product> products;
    float totalHarga;
    int rating;
    String komentar;

    public Transaction() {};

}
