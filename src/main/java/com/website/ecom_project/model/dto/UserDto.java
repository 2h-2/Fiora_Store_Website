package com.website.ecom_project.model.dto;
import java.util.Set;

import com.website.ecom_project.model.entity.Role;
import com.website.ecom_project.model.entity.User;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String address;
    private Set<Role> roles ;
    private String phone;

    public static UserDto toDto(User entity){
        return UserDto.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .roles(entity.getRoles())
        .address(entity.getAddress())
        .phone(entity.getPhone())
        .build();
    }

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.roles = user.getRoles();
        this.phone = user.getPhone();
    }
}
