package com.example.carent.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    public String message;

    public String email;

    public String token;

    public String refreshToken;

}
