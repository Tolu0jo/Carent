package com.example.carent.service.impl;

import com.example.carent.dto.request.SignInDto;
import com.example.carent.dto.request.SignUpDto;
import com.example.carent.dto.response.SignInResponse;
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

import java.util.HashMap;
import java.util.Map;
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

    @Override
    public SignUpResponse adminSignUp(SignUpDto signUpDto) {
        Optional<UserDetails> existingUser = userRepository.findByEmail(signUpDto.getEmail());
        if(existingUser.isPresent()) throw new UserAlreadyExistException("User already exist");
        if(!Objects.equals(signUpDto.password, signUpDto.confirmPassword)) throw new PasswordMismatchException("ConfirmPassword and password does not match");
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Roles.ADMIN)
                .build();
        User newUser = userRepository.save(user);
        String token = jwtService.generateToken(newUser);
        return SignUpResponse.builder()
                .message("Signed up successfully")
                .email(newUser.getEmail())
                .token(token).build();
    }

    @Override
    public SignInResponse signIn(SignInDto signInDto) {
        Optional<User> existingUser = Optional.ofNullable((User) userRepository.findByEmail(signInDto.getEmail()).orElseThrow(() -> new UserAlreadyExistException("User already exist")));
        if(existingUser.isEmpty()) throw new UserAlreadyExistException("Invalid credentials");
        if(!passwordEncoder.matches(signInDto.getPassword(), existingUser.get().getPassword())) throw new UserAlreadyExistException("Invalid credentials");
        jwtService.generateToken(existingUser.get());
        Map<String, Object> additionalClaims = new HashMap<>();
        additionalClaims.put("Role", existingUser.get().getRole());
        String token = jwtService.generateToken(existingUser.get());
       String refreshToken = jwtService.generateRefreshToken(additionalClaims,existingUser.get());
        return SignInResponse.builder()
                .message("Sign in successful!!")
                .email(existingUser.get().getUsername())
                .token(token)
                .refreshToken(refreshToken)
                .role(existingUser.get().getRole())
                .build();
    }

}
