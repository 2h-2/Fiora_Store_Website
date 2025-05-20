package com.website.ecom_project.model.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.website.ecom_project.model.dto.ProductDto;
import com.website.ecom_project.model.dto.ProductResponse;
import com.website.ecom_project.model.dto.ProductVariationDto;
import com.website.ecom_project.model.entity.Product;
import com.website.ecom_project.model.entity.ProductVariation;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "attributes", ignore = true)  
    @Mapping(target = "productVariations", ignore = true) 
    @Mapping(target = "cartItems", ignore = true) 
    @Mapping(target = "orderItems", ignore = true) 
    @Mapping(target = "reviews", ignore = true) 
    @Mapping(target = "categories", ignore = true) 
    Product toEntity(ProductDto dto);
    
    @Mapping(target = "categoriesIds", ignore = true) 
    ProductDto toDto(Product product);
    
    @Mapping(target = "categories", ignore = true)
    ProductResponse toResponse(Product product);

    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "sizes", ignore = true) 
    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "product", ignore = true)
    ProductVariation toVariation(ProductVariationDto product);


    List<ProductDto> toDtoList(List<Product> products);
    List<Product> toEntityList(List<ProductDto> dtos);
}
