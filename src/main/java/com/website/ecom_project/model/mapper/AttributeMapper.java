package com.website.ecom_project.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.website.ecom_project.model.dto.AttributeDto;
import com.website.ecom_project.model.entity.Attribute;
import com.website.ecom_project.model.entity.Color;
import com.website.ecom_project.model.entity.Size;


@Mapper(componentModel = "spring")
public interface AttributeMapper {
    
    @Mapping(target = "id", ignore = true) 
    Color toColorEntity(AttributeDto dto);

    @Mapping(target = "id", ignore = true) 
    Size toSizeEntity(AttributeDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productAttributes", ignore = true)
    Attribute toAttribute(AttributeDto dto);

}
