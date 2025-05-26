package com.website.ecom_project.model.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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


    private int inventory;
    private String sku;

    @Column(name="image_url")
    private String ImageURL;

    @Column(name="alt_text")
    private String AltText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id")
    private Size size;

    @OneToMany(mappedBy = "productVariation", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "productVariation")
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    @Transient
    @JsonProperty("productDescription")
    public String getProductDescription() {
        return product != null ? product.getDescription() : null;
    }

    @Transient
    public String getProductDescriptionAr() {
        return product != null ? product.getDescriptionAr() : null;
    }

}
