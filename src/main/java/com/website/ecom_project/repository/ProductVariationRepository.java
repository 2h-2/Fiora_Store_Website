package com.website.ecom_project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.website.ecom_project.model.entity.ProductVariation;

public interface ProductVariationRepository extends  JpaRepository<ProductVariation,Long>{
    List<ProductVariation> findAllByProductId(Long id);
    List<ProductVariation> findByProductId(Long id);
    Page<ProductVariation> findAllByProductId(Long productId, Pageable pageable);
}
