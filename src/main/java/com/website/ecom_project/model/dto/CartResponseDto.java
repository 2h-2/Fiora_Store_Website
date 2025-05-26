package com.website.ecom_project.model.dto;

import java.math.BigDecimal;

import com.website.ecom_project.model.entity.Color;
import com.website.ecom_project.model.entity.Size;

import lombok.Data;

@Data
public class CartResponseDto {
    private Long itemId;
    private String productDesc;
    private String productDescAr;
    private String altText;
    private String imageURL;
    private Color color;
    private Size size;
    private int quantity;
    private BigDecimal price;
}
