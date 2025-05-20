package com.website.ecom_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.AttributeProduct;

public interface AttributeProductRepository extends JpaRepository<AttributeProduct,Long> {
    List<AttributeProduct> findByProductId(Long productId);
}
