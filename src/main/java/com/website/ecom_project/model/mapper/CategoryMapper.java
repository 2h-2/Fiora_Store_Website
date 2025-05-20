package com.website.ecom_project.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.website.ecom_project.model.dto.CategoryDto;
import com.website.ecom_project.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "parentCategory", ignore = true) 
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDto dto);
}
