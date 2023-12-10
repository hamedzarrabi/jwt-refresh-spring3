package com.jwt.jwtsecurity.service.impl;

import com.jwt.jwtsecurity.dto.JwtAuthenticationResponse;
import com.jwt.jwtsecurity.dto.RefreshTokenRequest;
import com.jwt.jwtsecurity.dto.SigningRequest;
import com.jwt.jwtsecurity.dto.SignupRequest;
import com.jwt.jwtsecurity.entities.Role;
import com.jwt.jwtsecurity.entities.User;
import com.jwt.jwtsecurity.repository.UserRepository;
import com.jwt.jwtsecurity.service.AuthenticationService;
import com.jwt.jwtsecurity.service.JWTService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public User signup(SignupRequest signupRequest) {

        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signing(SigningRequest signingRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signingRequest.getEmail(), signingRequest.getPassword()));

        var user = userRepository.findByEmail(signingRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email or password.")
        );

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }



}
