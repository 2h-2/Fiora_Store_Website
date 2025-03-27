package com.website.ecom_project.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    
    Product toEntity(ProductDto dto);
}
