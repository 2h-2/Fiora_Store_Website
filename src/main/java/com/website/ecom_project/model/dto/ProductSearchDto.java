package com.website.ecom_project.model.dto;

import lombok.Data;

@Data
public class ProductSearchDto {
    private String productName;
    private String categoryName;
    private String sizeName;
    private String colorName;
}
