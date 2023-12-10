package com.jwt.jwtsecurity.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
