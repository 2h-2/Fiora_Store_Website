package com.website.ecom_project.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.website.ecom_project.model.dto.UserDto;
import com.website.ecom_project.model.dto.signUpDto;
import com.website.ecom_project.model.enums.RoleEnum;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;
    private String email;
    private String password;
    private String address;
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    public static User toEntity(signUpDto dto , Set<Role> roles){
        
        return User.builder()
        .userName(dto.getUserName())
        .email(dto.getEmail())
        .password(dto.getPassword())
        .address(dto.getAddress())
        .roles(roles)
        .phone(dto.getPhone())
        .build();
    }
}
