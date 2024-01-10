package com.example.carent.service.impl;

import com.example.carent.dto.request.SignUpDto;
import com.example.carent.dto.response.SignUpResponse;
import com.example.carent.exception.PasswordMismatchException;
import com.example.carent.exception.UserAlreadyExistException;
import com.example.carent.model.Roles;
import com.example.carent.model.User;
import com.example.carent.repository.UserRepository;
import com.example.carent.security.JwtService;
import com.example.carent.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public SignUpResponse userSignUp(SignUpDto signUpDto) {
        Optional<UserDetails> existingUser = userRepository.findByEmail(signUpDto.getEmail());
        if(existingUser.isPresent()) throw new UserAlreadyExistException("User already exist");
        if(!Objects.equals(signUpDto.password, signUpDto.confirmPassword)) throw new PasswordMismatchException("ConfirmPassword and password does not match");
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Roles.USER)
                .build();
        User newUser = userRepository.save(user);
        String token = jwtService.generateToken(newUser);
        return SignUpResponse.builder()
                .message("Signed up successfully")
                .email(newUser.getEmail())
                .token(token).build();
    }
}
