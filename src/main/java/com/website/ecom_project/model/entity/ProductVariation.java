package com.website.ecom_project.model.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_variation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)  
    private BigDecimal discount;


    private int quantity;
    private String sku;

    @Column(name="image_url")
    private String ImageURL;

    @Column(name="alt_text")
    private String AltText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_sizes",
        joinColumns = @JoinColumn(name = "productVar_id"),
        inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> sizes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_colors",
        joinColumns = @JoinColumn(name = "productVar_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors = new HashSet<>();

}
