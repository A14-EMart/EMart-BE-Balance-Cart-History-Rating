package com.a14.emart.backendbchr.DTO;

import com.a14.emart.backendbchr.models.ProductInTransaction;
import com.a14.emart.backendbchr.models.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetTransactionResponse {
    public String supermarketName;
    public String buyerName;
    public LocalDateTime tanggalWaktuPembelian;
    public float totalharga;
    public List<ProductInTransaction> products;
    public int rating;
    public String komentar;

    public GetTransactionResponse(Transaction transaction) {
        this.supermarketName = transaction.getSupermarketName();
        this.buyerName = transaction.getBuyerName();
        this.tanggalWaktuPembelian = transaction.getTanggalDanWaktuPembelian();
        this.totalharga = transaction.getTotalHarga();
        this.products = transaction.getProducts();
        this.rating = transaction.getRating();
        this.komentar = transaction.getKomentar();
    }
}
