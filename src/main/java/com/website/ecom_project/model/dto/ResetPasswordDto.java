package com.website.ecom_project.model.dto;
import lombok.*;

@Data
public class ResetPasswordDto {
    private String email;
    private String NewPassword;
}
