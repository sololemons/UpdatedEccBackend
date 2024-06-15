package com.AuthenticationDemo.Securtity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

// this may act as A Dto for registering a user
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
