package com.website.ecom_project.model.entity;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Table(name = "product_attributes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    @Column(name= "value_ar", nullable = false)
    private String valueAr;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;
}
