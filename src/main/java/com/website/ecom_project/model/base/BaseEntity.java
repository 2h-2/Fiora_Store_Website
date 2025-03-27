package com.website.ecom_project.model.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<Type> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Type id;


    @CreatedBy
    @Column(updatable = false)
    private String createdBy; 

    @LastModifiedBy
    private String updatedBy; 

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; 

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
