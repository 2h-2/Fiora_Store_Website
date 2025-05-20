package com.website.ecom_project.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReviewDto {
    private String comment;
    private BigDecimal rate;
    private Long userId;
    private Long productId;
}
