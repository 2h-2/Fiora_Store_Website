package com.website.ecom_project.model.entity;



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

    
}
