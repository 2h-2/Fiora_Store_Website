package com.website.ecom_project.model.dto;

import lombok.Data;

@Data
public class CheckOutDto {
    private Long userId;
    private String address;
    private String phone;
}
