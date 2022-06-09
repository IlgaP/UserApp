package com.example.userapp.signin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    private final String password;
    private final String email;
}
