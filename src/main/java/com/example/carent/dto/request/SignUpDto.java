package com.example.carent.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
public class SignUpDto {
    public String email;
    public String password;
    public String confirmPassword;
}
