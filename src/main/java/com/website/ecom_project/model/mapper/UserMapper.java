package com.website.ecom_project.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.website.ecom_project.model.dto.signUpDto;
import com.website.ecom_project.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)  
    @Mapping(target = "roles", ignore = true) 
    @Mapping(target = "enabled", constant = "false") 
    User signUpDtoToUser(signUpDto dto);

    
}
