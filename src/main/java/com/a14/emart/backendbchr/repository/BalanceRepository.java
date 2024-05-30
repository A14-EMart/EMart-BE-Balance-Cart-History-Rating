package com.a14.emart.backendbchr.repository;

import com.a14.emart.backendbchr.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByUserId(Long userId);
}
