package com.a14.emart.backendbchr.repository;

import com.a14.emart.backendbchr.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findTransactionByPembeliId(long idPembeli);
    List<Transaction> findTransactionByPembeliIdAndSupermarketId(long idPembeli, UUID idSupermarket);
    List<Transaction> findTransactionBySupermarketId(UUID supermarketId);
}
