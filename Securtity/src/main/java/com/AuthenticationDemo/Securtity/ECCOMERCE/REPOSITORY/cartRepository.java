package com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface cartRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProduct_ProductId( Integer productId);
}
