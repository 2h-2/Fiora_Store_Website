package com.website.ecom_project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.website.ecom_project.model.dto.LoginDto;
import com.website.ecom_project.model.dto.LoginResponse;
import com.website.ecom_project.model.dto.signUpDto;
import com.website.ecom_project.model.entity.Role;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.enums.RoleEnum;
import com.website.ecom_project.repository.RoleRepository;
import com.website.ecom_project.repository.UserRepository;
import com.website.ecom_project.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired 
    UserRepository userRepo;

    @Autowired 
    RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto){
        Optional<User> user = userRepo.findByEmail(loginDto.getEmail());
        if (user.isEmpty() || !authService.isValidPassword(loginDto.getPassword(), user.get().getPassword())) {
            return new ResponseEntity<>("Email or Password is not correct!", HttpStatus.BAD_REQUEST);
        }

        LoginResponse response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody signUpDto user){

        if(userRepo.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        Set<Role> roles = new HashSet<>();

        user.getRoles().forEach(role -> {
            Role roleObj = roleRepository.findByName(RoleEnum.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Error: Role "+ role +" not found."));
                roles.add(roleObj);
        });

        authService.signUp(User.toEntity(user, roles));
        return new ResponseEntity<>("Registration Success.", HttpStatus.OK);

    }
}
/*
 switch (role.toLowerCase()) {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleEnum.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Error: Role ADMIN not found."));
                roles.add(adminRole);
                break;
            default:
                Role userRole = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role USER not found."));
                roles.add(userRole);
        }
 */