package com.website.ecom_project.service;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.entity.Category;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.model.mapper.ProductMapper;
import com.website.ecom_project.repository.CategoryRepository;
import com.website.ecom_project.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper productMapper;


    
    public void create(ProductDto dto){
        
        
        Product product = productMapper.toEntity(dto);
        
        
        if (dto.getCategoriesIds().isEmpty()) {
            throw new RuntimeException("No category IDs provided");
        }
        Set<Category> categories = dto.getCategoriesIds().stream()
        .map(id -> {
            System.out.println("Looking for Category ID: " + id); 
            return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
        })
        .collect(Collectors.toSet());        product.setCategories(categories);
        
    
        productRepo.save(product); 
    }

} 