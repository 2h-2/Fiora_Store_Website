package com.website.ecom_project.service;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.model.mapper.ProductMapper;
import com.website.ecom_project.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public void create(ProductDto dto){
        Product product = ProductMapper.INSTANCE.toEntity(dto);
        productRepo.save(product);
    }
}
