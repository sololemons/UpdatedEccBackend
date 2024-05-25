package com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category,Integer> {
}
