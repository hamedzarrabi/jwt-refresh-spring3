package com.jwt.jwtsecurity.service;

import com.jwt.jwtsecurity.dto.JwtAuthenticationResponse;
import com.jwt.jwtsecurity.dto.RefreshTokenRequest;
import com.jwt.jwtsecurity.dto.SigningRequest;
import com.jwt.jwtsecurity.dto.SignupRequest;
import com.jwt.jwtsecurity.entities.User;


public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    JwtAuthenticationResponse signing(SigningRequest signingRequest);
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
