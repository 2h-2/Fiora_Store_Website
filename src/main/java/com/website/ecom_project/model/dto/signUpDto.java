package com.website.ecom_project.model.dto;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.website.ecom_project.model.entity.Role;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.enums.RoleEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class signUpDto {
    private String email;
    private String password;
    private String userName;
    private Set<String> roles;
    private String address;
    private String phone;

    public static signUpDto toUser(User user){
      
        return signUpDto.builder()
        .email(user.getEmail())
        .userName(user.getUserName())
        .password(user.getPassword())
        .address(user.getAddress())
        .phone(user.getPhone())
        .roles(user.getRoles().stream()
                        .map(role -> role.getName().name()) 
                        .collect(Collectors.toSet()))
        .build();
    }
}
