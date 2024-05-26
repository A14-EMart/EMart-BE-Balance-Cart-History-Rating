package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.models.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    public Transaction create(Transaction transaction);
    public List<Transaction> findByBuyer(long buyerId);
    public List<Transaction> findyByBuyerPerSupermarket(long buyerId, UUID supermarketId);
    public List<Transaction> findBySupermarket(UUID supermarketId);
}
