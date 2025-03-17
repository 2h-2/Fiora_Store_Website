package com.website.ecom_project.repository;

import com.website.ecom_project.model.entity.Role;
import com.website.ecom_project.model.enums.RoleEnum;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role>  findByName(RoleEnum name);
}
