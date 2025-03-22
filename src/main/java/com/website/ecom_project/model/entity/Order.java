package com.website.ecom_project.model.entity;
import java.math.BigDecimal;
import java.util.*;

import com.website.ecom_project.model.enums.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "carts")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "total_price")
    private BigDecimal totalPrice;

    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @OneToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

}
