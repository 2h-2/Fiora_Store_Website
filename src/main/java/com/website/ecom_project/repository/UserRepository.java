package com.website.ecom_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationToken(String token);
    Boolean existsByEmail(String email);

}
