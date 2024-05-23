package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    public Transaction create(Transaction transaction);
    public List<Transaction> findByBuyer(UUID buyerId);
    public List<Transaction> findyByBuyerPerSupermarket(UUID buyerId, UUID supermarketId);
    public List<Transaction> findBySupermarket(UUID supermarketId);
}
