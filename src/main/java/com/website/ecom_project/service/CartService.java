package com.website.ecom_project.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.AddToCartRequestDto;
import com.website.ecom_project.model.dto.CartResponseDto;
import com.website.ecom_project.model.entity.Cart;
import com.website.ecom_project.model.entity.CartItem;
import com.website.ecom_project.model.entity.ProductVariation;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.repository.CartItemRepository;
import com.website.ecom_project.repository.CartRepository;
import com.website.ecom_project.repository.ProductVariationRepository;
import com.website.ecom_project.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final UserRepository userRepo;
    private final ProductVariationRepository productVarRepo;

    @Transactional
    public void AddToCart(AddToCartRequestDto dto){
        User user = userRepo.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        ProductVariation product = productVarRepo.findById(dto.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepo.save(newCart);
        });

        Optional<CartItem> existingItem = cart.getItems().stream()
        .filter(item -> item.getProductVariation().getId().equals(dto.getProductId())).findFirst();

        if(existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
            cartItemRepo.save(item);
        }else{
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductVariation(product);
            cartItem.setQuantity(dto.getQuantity());

            cart.getItems().add(cartItem);
        }

        cartRepo.save(cart);
    }

    public List<CartResponseDto> getCartByUser(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
       
        Cart cart = cartRepo.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartResponseDto> response = new ArrayList<>();

        for(CartItem item : cart.getItems()){

            CartResponseDto res = new CartResponseDto();
            ProductVariation productVar = item.getProductVariation();
            res.setItemId(cart.getId());
            res.setPrice(item.getProductPrice());
            res.setQuantity(item.getQuantity());
            res.setAltText(productVar.getAltText());
            res.setImageURL(productVar.getImageURL());
            res.setColor(productVar.getColor());
            res.setSize(productVar.getSize());
            res.setProductDesc(productVar.getProductDescription());
            res.setProductDescAr(productVar.getProductDescriptionAr());
            
            response.add(res);
            
        }
        return response;
    }

    public void clearCart(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Cart cart = cartRepo.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();
        cartRepo.save(cart);
    }

    
    public CartItem getCartItem(Long itemId){
        return cartItemRepo.findById(itemId).orElseThrow(
            () -> new RuntimeException("Item not found")
        );
    }
    
    public void deleteItem(Long itemId) {
        cartItemRepo.findById(itemId).orElseThrow(
            () -> new RuntimeException("Item not found")
        );

        cartItemRepo.deleteById(itemId);
    }

}
