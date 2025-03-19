package com.website.ecom_project.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.website.ecom_project.error.OTPExpiredException;
import com.website.ecom_project.model.dto.ForgotPasswordDto;
import com.website.ecom_project.model.dto.LoginDto;
import com.website.ecom_project.model.dto.LoginResponse;
import com.website.ecom_project.model.dto.ResetPasswordDto;
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
        
        User userObj = User.toEntity(user, roles);
        authService.signUp(userObj);
        return new ResponseEntity<>("Registration Success.", HttpStatus.OK);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDto request) {
        try {
            authService.sendForgotPasswordEmail(request.getEmail());
            return ResponseEntity.ok("Password reset email sent successfully");

        } catch (OTPExpiredException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestParam String email, @RequestParam String otp) {
        try {
            authService.verifyOTP(email, otp); 
            return ResponseEntity.ok("OTP verified successfully");

        } catch (OTPExpiredException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OTP verification failed: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyYoken(@RequestParam String token) {
        try {

            authService.verifyToken(token); 
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(" Verifiecation successfully");
        
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred" + ex);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto request) {
        try {
            authService.resetPassword(request);
            return ResponseEntity.ok("Password reset successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}