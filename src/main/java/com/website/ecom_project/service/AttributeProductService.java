package com.website.ecom_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.AttributeProductDto;
import com.website.ecom_project.model.entity.Attribute;
import com.website.ecom_project.model.entity.AttributeProduct;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.repository.AttributeProductRepository;
import com.website.ecom_project.repository.AttributeRepository;
import com.website.ecom_project.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttributeProductService {
        private final AttributeProductRepository attributeProductRepo;
        private final AttributeRepository attributeRepo;
        private final ProductRepository productRepo;

    public AttributeProduct save(AttributeProductDto dto) {
        Attribute attribute = attributeRepo.findById(dto.getAttributeId())
        .orElseThrow(() -> new RuntimeException("Attribute not found"));

        Product product = productRepo.findById(dto.getProductId())
        .orElseThrow(() -> new RuntimeException("Product not found"));

        AttributeProduct attributeProduct = new AttributeProduct();
        attributeProduct.setAttribute(attribute);
        attributeProduct.setProduct(product);
        attributeProduct.setValue(dto.getValue());
        attributeProduct.setValueAr(dto.getValueAr());

        return attributeProductRepo.save(attributeProduct);
    }

    public List<AttributeProduct> getAll() {
        return attributeProductRepo.findAll();
    }

    public List<AttributeProduct> getByProductId(Long productId) {
        return attributeProductRepo.findByProductId(productId);
    }

    public void deleteById(Long id) {
        if (!attributeProductRepo.existsById(id)) {
        throw new RuntimeException("Attribute not found with ID: " + id);
    }
        attributeProductRepo.deleteById(id);
    }
}
