package com.website.ecom_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.dto.ProductVariationDto;
import com.website.ecom_project.service.ProductService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        try {
        
            productService.create(productDto);
            return new ResponseEntity<>("Product Created Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try {            
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    } 

    @GetMapping
    public ResponseEntity<?> getAllProduct(@RequestParam(defaultValue = "0") int page, 
                                            @RequestParam(defaultValue = "10") int size)
    {
        try {            
            return new ResponseEntity<>(productService.getAllProduct(page, size), HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    } 


    @PostMapping("/var")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProductVariation(@RequestBody ProductVariationDto productDto){
        try {
            System.out.println("Test....." );
            productService.createProductVarition(productDto);

            return new ResponseEntity<>("Product Created Successsfully.", HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    } 


    @GetMapping("/{id}/varProducts")
    public ResponseEntity<?> getProduct(@PathVariable Long id,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size)
    {
        try {            
            return new ResponseEntity<>(productService.getProductVariation(id, page, size), HttpStatus.OK);
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } 
    } 
}
