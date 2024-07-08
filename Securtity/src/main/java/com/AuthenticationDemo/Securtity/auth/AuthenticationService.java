package com.AuthenticationDemo.Securtity.auth;

import com.AuthenticationDemo.Securtity.Config.JwtService;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.Role;
import com.AuthenticationDemo.Securtity.ECCOMERCE.REPOSITORY.Userrepository;
import com.AuthenticationDemo.Securtity.ECCOMERCE.Entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Userrepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()

                )
        );
var user  = repository.findByEmail(request.getEmail())
        .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().
                token(jwtToken)
                .build();

    }
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().
                token(jwtToken)
                .build();

    }
}
