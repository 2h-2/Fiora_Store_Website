package com.website.ecom_project.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponseUser {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
}
