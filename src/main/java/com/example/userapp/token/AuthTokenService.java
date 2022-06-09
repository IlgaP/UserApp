package com.example.userapp.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public void saveAuthToken(AuthToken token) {
        authTokenRepository.save(token);
    }



}
