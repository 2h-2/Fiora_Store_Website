package com.website.ecom_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.entity.Wishlist;


public interface WishListRepository extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> findByUser(User user);
    //List<Wishlist> findAllByUser(User user);
}


