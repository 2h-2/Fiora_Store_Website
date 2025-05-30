package com.website.ecom_project.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.website.ecom_project.model.entity.Product;

public class ProductSpecification  {

    
    public static Specification<Product> hasCategory(String categoryName){
        return (root, query, builder ) ->
            builder.equal(root.get("categories").get("name"), categoryName);
    
    }

    public static Specification<Product> hasSize(String sizeName){
        return (root, query, builder ) ->
            builder.equal(root.get("sizes").get("name"), sizeName);
    
    }

    public static Specification<Product> hasColor(String colorName){
        return (root, query, builder ) ->
            builder.equal(root.get("colors").get("name"), colorName);
    
    }

    public static Specification<Product> hasNameLike(String name){
        return (root, query, builder ) ->
            builder.equal(root.get("name"), name);
    
    }
}
