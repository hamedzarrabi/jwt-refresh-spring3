package com.jwt.jwtsecurity.dto;

import lombok.Data;

@Data
public class SigningRequest {
    private String email;
    private String password;
}
