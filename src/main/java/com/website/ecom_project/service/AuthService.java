package com.website.ecom_project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.website.ecom_project.model.dto.LoginDto;
import com.website.ecom_project.model.dto.LoginResponse;
import com.website.ecom_project.model.dto.UserDto;
import com.website.ecom_project.model.dto.signUpDto;
import com.website.ecom_project.model.entity.User;
import com.website.ecom_project.repository.UserRepository;
import org.springframework.security.core.Authentication;


@Service
public class AuthService {

    @Autowired 
    UserRepository userRepo;
    
    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;
    
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

    public void signUp(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
