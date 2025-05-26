package com.website.ecom_project.model.dto;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
