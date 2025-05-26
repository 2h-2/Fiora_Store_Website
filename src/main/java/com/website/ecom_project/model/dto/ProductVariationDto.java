package com.website.ecom_project.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductVariationDto {
    private BigDecimal price;
    private BigDecimal discount;
    private int quantity;
    private String sku;
    private String imageURL;
    private String altText;

    private Long productId;
    private Long sizeId;
    private Long colorId;
}
