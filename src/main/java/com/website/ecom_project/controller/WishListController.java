package com.website.ecom_project.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.website.ecom_project.model.dto.WishListDto;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishlistService;

    @PostMapping
    public ResponseEntity<?> addToWishlist(@RequestBody WishListDto dto) {
        try {
            
            wishlistService.addToWishList(dto);
            return ResponseEntity.ok("Product added to wishlist.");
            
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } 
    }

    @GetMapping
    public ResponseEntity<?> getWishlist(@RequestParam Long userId) {
        try {

            Set<Product> wishlist = wishlistService.getWishlistByUserId(userId);
            return ResponseEntity.ok(wishlist);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } 
        
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromWishlist(@RequestBody WishListDto dto) {
        try {

            wishlistService.removeFromWishlist(dto);
            return ResponseEntity.ok("Product removed from wishlist.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
    }
}
