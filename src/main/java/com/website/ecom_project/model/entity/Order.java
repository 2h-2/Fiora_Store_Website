package com.website.ecom_project.model.entity;
import java.math.BigDecimal;
import java.util.*;

import com.website.ecom_project.model.base.BaseEntity;
import com.website.ecom_project.model.enums.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "orders")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity<Long> {

    @Column(name= "total_price")
    private BigDecimal totalPrice;

    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
    
    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

}
