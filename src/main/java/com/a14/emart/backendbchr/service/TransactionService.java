package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Pembeli;
import com.a14.emart.backendbchr.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    public Transaction create(Transaction transaction);
    public List<Transaction> findAll();
    public Transaction findById(UUID id);
    public List<Transaction> findByBuyer(Pembeli pembeli);
    public void deleteById(UUID id);

}
