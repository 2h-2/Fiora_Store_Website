package com.website.ecom_project.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mapping(target = "attributes", ignore = true)  
    @Mapping(target = "productVariations", ignore = true) 
    @Mapping(target = "cartItems", ignore = true) 
    @Mapping(target = "orderItems", ignore = true) 
    @Mapping(target = "reviews", ignore = true) 
    @Mapping(target = "categories", ignore = true) 
    Product toEntity(ProductDto dto);
    
    @Mapping(target = "ids", ignore = true) 
    ProductDto toDto(Product product);

}
