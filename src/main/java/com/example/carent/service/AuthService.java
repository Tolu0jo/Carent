package com.example.carent.service;

import com.example.carent.dto.request.SignUpDto;
import com.example.carent.dto.response.SignUpResponse;
import org.springframework.stereotype.Service;

public interface AuthService {
     SignUpResponse userSignUp (SignUpDto signUpDto);
}
