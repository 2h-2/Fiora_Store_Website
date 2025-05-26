package com.website.ecom_project.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String phone;
    private String status;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
}
