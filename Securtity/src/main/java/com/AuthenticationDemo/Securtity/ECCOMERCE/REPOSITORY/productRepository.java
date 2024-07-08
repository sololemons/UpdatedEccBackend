package com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface productRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory_CategoryId(Integer categoryId);

    List<Product> findByProductNameContaining(String query);
}
