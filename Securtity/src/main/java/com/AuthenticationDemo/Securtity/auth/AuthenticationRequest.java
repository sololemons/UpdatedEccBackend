package com.AuthenticationDemo.Securtity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// this may Act As A Dto for Authenticating the Arleady signed Up Users
public class AuthenticationRequest {
     private String email;
    private String password;

}
