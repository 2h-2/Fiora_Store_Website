package com.website.ecom_project.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.WishListDto;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.entity.Wishlist;
import com.website.ecom_project.repository.ProductRepository;
import com.website.ecom_project.repository.UserRepository;
import com.website.ecom_project.repository.WishListRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {
        private final WishListRepository wishListRepo;
        private final UserRepository userRepo;
        private final ProductRepository productRepo;


        public void addToWishList(WishListDto dto){
                User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

                Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));

                Wishlist wishlist = wishListRepo.findByUser(user)
                .orElseGet(() -> {
                        Wishlist newWishlist = new Wishlist();
                        newWishlist.setUser(user);
                        return wishListRepo.save(newWishlist);
                });
                
                wishlist.getProducts().add(product);
                wishListRepo.save(wishlist);
        }

        public Set<Product> getWishlistByUserId(Long userId) {
                User user = userRepo.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                Wishlist wishlist = wishListRepo.findByUser(user)
                        .orElseThrow(() -> new RuntimeException("Wishlist not found"));

                return wishlist.getProducts();
}

        public void removeFromWishlist(WishListDto dto) {
                User user = userRepo.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

                Wishlist wishlist = wishListRepo.findByUser(user)
                                        .orElseThrow(() -> new RuntimeException("Wishlist not found"));

                Product product = productRepo.findById(dto.getProductId())
                                        .orElseThrow(() -> new RuntimeException("Product not found"));

                wishlist.getProducts().remove(product); 
                wishListRepo.save(wishlist);         
}
}
