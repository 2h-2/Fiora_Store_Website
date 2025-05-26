package com.website.ecom_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.Order;
import com.website.ecom_project.model.entity.User;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
