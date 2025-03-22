package com.website.ecom_project.model.entity;

import lombok.*;

import java.math.BigDecimal;

import jakarta.persistence.*;


@Table(name = "reviews")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @Column(nullable = false, precision = 2, scale = 1)  // Matches DECIMAL(2,1)
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
