package com.website.ecom_project.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.website.ecom_project.model.dto.SignUpDto;
import com.website.ecom_project.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)  
    @Mapping(target = "roles", ignore = true) 
    @Mapping(target = "enabled", ignore = true) 
    @Mapping(target = "otp", ignore = true) 
    @Mapping(target = "otpExpiry", ignore = true) 
    @Mapping(target = "verificationToken",ignore = true)
    @Mapping(target = "reviews",ignore = true) 
    @Mapping(target = "wishlist",ignore = true) 
    @Mapping(target = "cart",ignore = true) 
    User signUpDtoToUser(SignUpDto dto);

    @Mapping(target = "roles", ignore = true) 
    SignUpDto toDto(User user);
    
}
