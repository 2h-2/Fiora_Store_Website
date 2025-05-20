package com.website.ecom_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.ecom_project.model.dto.ReviewDto;
import com.website.ecom_project.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ProductService reviewService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDto dto){
        try {
        
            reviewService.addReview(dto);
            return new ResponseEntity<>("Review Added Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getAllReviewsByProductId(@PathVariable Long productId){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviewByProductId(productId));
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
