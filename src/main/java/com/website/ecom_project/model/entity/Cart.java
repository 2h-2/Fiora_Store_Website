package com.website.ecom_project.model.entity;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "carts")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy= "cart", cascade = CascadeType.ALL)
    private Set<CartItem> items = new HashSet<>();
}
