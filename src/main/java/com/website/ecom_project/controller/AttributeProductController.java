package com.website.ecom_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.ecom_project.model.dto.AttributeProductDto;
import com.website.ecom_project.model.entity.AttributeProduct;
import com.website.ecom_project.service.AttributeProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attribute-product")
public class AttributeProductController {
    private final AttributeProductService attributeProductService;

    @PostMapping
    public ResponseEntity<AttributeProduct> create(@RequestBody AttributeProductDto attributeProduct) {
        return ResponseEntity.ok(attributeProductService.save(attributeProduct));
    }

    @GetMapping
    public ResponseEntity<List<AttributeProduct>> getAll() {
        return ResponseEntity.ok(attributeProductService.getAll());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<AttributeProduct>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(attributeProductService.getByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        attributeProductService.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
