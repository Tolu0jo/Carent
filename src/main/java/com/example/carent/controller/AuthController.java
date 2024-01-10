package com.example.carent.controller;

import com.example.carent.dto.request.SignUpDto;
import com.example.carent.dto.response.SignUpResponse;
import com.example.carent.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RequestMapping("/signup")
    @PostMapping
    public ResponseEntity<SignUpResponse> userSignUp(@RequestBody SignUpDto signUpDto){
        SignUpResponse response = authService.userSignUp(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
