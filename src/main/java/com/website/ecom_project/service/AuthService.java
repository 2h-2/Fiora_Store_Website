package com.website.ecom_project.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.website.ecom_project.error.OTPExpiredException;
import com.website.ecom_project.model.dto.LoginDto;
import com.website.ecom_project.model.dto.LoginResponse;
import com.website.ecom_project.model.dto.ResetPasswordDto;
import com.website.ecom_project.model.dto.UserDto;
import com.website.ecom_project.model.dto.SignUpDto;
import com.website.ecom_project.model.entity.Role;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.model.enums.RoleEnum;
import com.website.ecom_project.model.mapper.UserMapper;
import com.website.ecom_project.repository.RoleRepository;
import com.website.ecom_project.repository.UserRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired 
    UserRepository userRepo;

    @Autowired
    EmailService emailService;
    
    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    RoleRepository roleRepository;

    @Autowired
    JwtService jwtService;

    private final UserMapper userMapper;
    
    public LoginResponse login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String  token = jwtService.generateToken(authentication);

        Optional<User> user = userRepo.findByEmail(loginDto.getEmail());

        return new LoginResponse(UserDto.toDto(user.get()), token);
    }

    public boolean isValidPassword(String rawPassword, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, hashedPassword);
}

    public void signUp(SignUpDto dto){


        Set<Role> roles = assignRoles(dto.getRoles());

        User user = userMapper.signUpDtoToUser(dto);
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.generateVerificationToken();
        user.setEnabled(false);
        userRepo.save(user);

        try {
            emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());
        } catch (MessagingException e) {
            System.err.println("Failed to send verification email: " + e.getMessage());
        }
    }

    public void sendForgotPasswordEmail(String email) {
        Optional<User> userObj = userRepo.findByEmail(email);
        if (userObj.isEmpty()) {
            throw new OTPExpiredException("User with email " + email + " not found");
        }

        User user = userObj.get();
        user.generateOTP();
        userRepo.save(user);

        emailService.sendOtpEmail(user.getEmail(), user.getOtp());
    }

    public void verifyOTP(String email, String otp) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new OTPExpiredException("User with email " + email + " not found");
        }

        User user = userOptional.get();
        if (!user.validateOtp(otp)) {
            throw new OTPExpiredException("Invalid OTP");
        }

        user.clearOtp();
        userRepo.save(user);

    }

    public void verifyToken(String token) {
        Optional<User> userOptional = userRepo.findByVerificationToken(token);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEnabled(true);
            user.setVerificationToken(null);
            userRepo.save(user);
        
        } else {
            throw new NoSuchElementException("Invalid or expired token.");
        }

    }

    public void resetPassword(ResetPasswordDto request) {
        Optional<User> userObj = userRepo.findByEmail(request.getEmail());
        if (userObj.isEmpty()) {
            throw new OTPExpiredException("User with email " + request.getEmail() + " not found");
        }

        User user = userObj.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
    }

    private Set<Role> assignRoles(Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        
        roleNames.forEach(role -> {
            Role roleObj = roleRepository.findByName(RoleEnum.valueOf(role))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Role '" + role + "' not found."));
            roles.add(roleObj);
        });

        return roles;
    }
}
