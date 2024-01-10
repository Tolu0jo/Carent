package com.example.carent.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignInDto {
    public String email;
    public String password;
}
