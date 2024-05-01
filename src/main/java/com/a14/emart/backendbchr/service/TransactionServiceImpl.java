package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Pembeli;
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
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> findByBuyer(Pembeli pembeli) {
        return transactionRepository.findTransactionByPembeli(pembeli);
    }

    @Override
    public void deleteById(UUID id) {
        transactionRepository.deleteById(id);
    }
}
