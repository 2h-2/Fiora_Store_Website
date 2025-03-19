package com.website.ecom_project.model.entity;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.website.ecom_project.model.dto.signUpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;
    @Column(unique = true)
    private String phone;

    private String otp;
    private LocalDateTime otpExpiry;

    private String verificationToken;
    private boolean enabled = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    public static User toEntity(signUpDto dto , Set<Role> roles){
        
        return User.builder()
        .userName(dto.getUserName())
        .email(dto.getEmail())
        .password(dto.getPassword())
        .address(dto.getAddress())
        .roles(roles)
        .phone(dto.getPhone())
        .build();
    }

    public void generateOTP(){
        this.otp = generateRandomOtp();
        this.otpExpiry = LocalDateTime.now().plusMinutes(5);
    }

    public void clearOtp(){
        this.otp = null;
        this.otpExpiry = null;
    }

    public boolean validateOtp(String enteredOtp){
        if (this.otp == null || this.otpExpiry == null) {
            return false; 
        }

        if (LocalDateTime.now().isAfter(otpExpiry)) {
            clearOtp();
            return false;
        }

        return this.otp.equals(enteredOtp);
    }

    public String generateRandomOtp(){
        SecureRandom random = new SecureRandom();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }

    public void generateVerificationToken(){
        this.verificationToken =  UUID.randomUUID().toString();
    }

    public void regenerateVerificationToken() {
        this.verificationToken = UUID.randomUUID().toString();
        this.enabled = false;
    }

    public boolean isAccountVerified() {
        return enabled;
    }
}
