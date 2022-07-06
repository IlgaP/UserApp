package com.example.userapp.signin;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/sign-in")
public class SignInController {

    private final SignInService signInService;

    @PostMapping
    public String signIn(@RequestBody SignInRequest signInRequest) {

        return signInService.signIn(signInRequest);
    }
}
