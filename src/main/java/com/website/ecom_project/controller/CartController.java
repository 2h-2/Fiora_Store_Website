package com.website.ecom_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.ecom_project.model.dto.AddToCartRequestDto;
import com.website.ecom_project.model.dto.CartResponseDto;
import com.website.ecom_project.model.entity.CartItem;
import com.website.ecom_project.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

   
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Long userId) {
        try {
            List<CartResponseDto> cartItems = cartService.getCartByUser(userId);
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody AddToCartRequestDto dto) {
        try {
            cartService.AddToCart(dto);
            return new ResponseEntity<>("Item added to cart successfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        
    }


    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        try {
            cartService.clearCart(userId);
            return new ResponseEntity<>("Cart cleared successfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        try {
            cartService.deleteItem(itemId);
            return new ResponseEntity<>("Item deleted from cart.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/item/{itemId}")
    
    public ResponseEntity<?> getCartItem(@PathVariable Long itemId) {
        try {
            CartItem item = cartService.getCartItem(itemId);
            return new ResponseEntity<>(item, HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long id) {
        try {
            cartService.deleteItem(id);
            return new ResponseEntity<>("Item removed from cart.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
