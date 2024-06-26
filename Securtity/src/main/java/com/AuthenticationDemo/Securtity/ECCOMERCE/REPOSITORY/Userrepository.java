package com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY;

import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Userrepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
