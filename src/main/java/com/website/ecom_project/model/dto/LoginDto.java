package com.website.ecom_project.model.dto;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;


@Data
public class LoginDto {
    @Email(message = "Email should be valid")
    private String email;
    @Min(value= 8, message = "Password must be at least 18")
    private String password;
}
