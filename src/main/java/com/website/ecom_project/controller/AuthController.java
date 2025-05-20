package com.website.ecom_project.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.website.ecom_project.error.OTPExpiredException;
import com.website.ecom_project.model.dto.ForgotPasswordDto;
import com.website.ecom_project.model.dto.LoginDto;
import com.website.ecom_project.model.dto.LoginResponse;
import com.website.ecom_project.model.dto.ResetPasswordDto;
import com.website.ecom_project.model.dto.SignUpDto;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.repository.RoleRepository;
import com.website.ecom_project.repository.UserRepository;
import com.website.ecom_project.service.AuthService;
import jakarta.validation.Valid;

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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Optional<User> user = userRepo.findByEmail(loginDto.getEmail());
        if (user.isEmpty() || !authService.isValidPassword(loginDto.getPassword(), user.get().getPassword())) {
            return new ResponseEntity<>("Email or Password is not correct!", HttpStatus.BAD_REQUEST);
        }

        LoginResponse response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto user){

        if(userRepo.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        authService.signUp(user);
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