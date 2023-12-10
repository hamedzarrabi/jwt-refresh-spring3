package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.dto.JwtAuthenticationResponse;
import com.jwt.jwtsecurity.dto.RefreshTokenRequest;
import com.jwt.jwtsecurity.dto.SigningRequest;
import com.jwt.jwtsecurity.dto.SignupRequest;
import com.jwt.jwtsecurity.entities.User;
import com.jwt.jwtsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signing(@RequestBody SigningRequest signingRequest) {
        return ResponseEntity.ok(authenticationService.signing(signingRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
