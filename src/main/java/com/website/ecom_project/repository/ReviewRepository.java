package com.website.ecom_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductId(Long productId);
}
