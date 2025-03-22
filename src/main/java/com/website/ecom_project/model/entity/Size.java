package com.website.ecom_project.model.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "sizes")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name= "name_ar", nullable = false)
    private String nameAr;

    @ManyToMany(mappedBy = "sizes")
    @JsonIgnore
    private Set<ProductVariation> productVariations = new HashSet<>();
}
