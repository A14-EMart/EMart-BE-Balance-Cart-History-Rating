package com.a14.emart.backendbchr.repository;
import com.a14.emart.backendbchr.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findCartItemByPembeliId(Long pembeliId);
}