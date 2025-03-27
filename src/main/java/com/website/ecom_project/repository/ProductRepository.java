package com.website.ecom_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.website.ecom_project.model.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
