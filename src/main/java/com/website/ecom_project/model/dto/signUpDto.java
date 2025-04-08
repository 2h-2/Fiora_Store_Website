package com.website.ecom_project.model.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpDto {
    private String email;
    private String password;
    private String userName;
    private Set<String> roles;
    private String address;
    private String phone;

}
