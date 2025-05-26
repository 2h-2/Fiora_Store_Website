package com.website.ecom_project.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy= "cart", cascade = CascadeType.ALL)
    private Set<CartItem> items = new HashSet<>();
}
