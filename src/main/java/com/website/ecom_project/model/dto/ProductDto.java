package com.website.ecom_project.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import com.website.ecom_project.model.entity.Category;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private BigDecimal productPrice;
    private Set<Category> categories = new HashSet<>();
}
