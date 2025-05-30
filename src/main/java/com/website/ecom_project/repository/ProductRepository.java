package com.website.ecom_project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.website.ecom_project.model.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>{
    @EntityGraph(attributePaths = "categories")
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
}
