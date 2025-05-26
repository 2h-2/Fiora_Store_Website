package com.website.ecom_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.Cart;
import com.website.ecom_project.model.entity.User;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
