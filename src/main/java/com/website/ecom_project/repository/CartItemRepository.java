package com.website.ecom_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{
    
}
