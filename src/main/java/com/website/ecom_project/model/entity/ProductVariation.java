package com.website.ecom_project.model.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Table(name = "product_variation")
@Entity
@Data
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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_sizes",
        joinColumns = @JoinColumn(name = "productVar_id"),
        inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> sizes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_colors",
        joinColumns = @JoinColumn(name = "productVar_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors = new HashSet<>();

}
