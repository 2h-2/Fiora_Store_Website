package com.website.ecom_project.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String nameAr;
    private String description;
    private String descriptionAr;
    private BigDecimal productPrice;
    private Set<String> categories = new HashSet<>();
}
