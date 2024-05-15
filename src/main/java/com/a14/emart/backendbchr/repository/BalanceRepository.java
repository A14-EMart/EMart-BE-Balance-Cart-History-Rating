package com.a14.emart.backendbchr.repository;

import com.a14.emart.backendbchr.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {
    Balance findByUserId(UUID userId);
}
