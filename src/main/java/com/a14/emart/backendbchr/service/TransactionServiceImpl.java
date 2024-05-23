package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Transaction;
import com.a14.emart.backendbchr.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByBuyer(UUID buyerId) {
        return transactionRepository.findTransactionByPembeliId(buyerId);
    }

    @Override
    public List<Transaction> findyByBuyerPerSupermarket(UUID buyerId, UUID supermarketId) {
        return transactionRepository.findTransactionByPembeliIdAndSupermarketId(buyerId, supermarketId);
    }

    @Override
    public List<Transaction> findBySupermarket(UUID supermarketId) {
        return transactionRepository.findTransactionBySupermarketId(supermarketId);
    }
}
