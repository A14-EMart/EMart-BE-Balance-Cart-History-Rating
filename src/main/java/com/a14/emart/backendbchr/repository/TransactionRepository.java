package com.a14.emart.backendbchr.repository;

import com.a14.emart.backendbchr.model.Pembeli;
import com.a14.emart.backendbchr.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    public List<Transaction> findTransactionByPembeli(Pembeli pembeli);
}
