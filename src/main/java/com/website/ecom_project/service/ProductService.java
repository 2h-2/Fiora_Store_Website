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
        Set<Category> categories = dto.getIds().stream().map(id -> categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found: " + id))).collect(Collectors.toSet());
        
        

        product.setCategories(categories);
        categoryRepo.saveAll(categories);
        for (Category category : categories) {
            category.getProducts().add(product);
        }
        

        productRepo.save(product);
    }

} 