package com.example.userapp.signin;

import com.example.userapp.appuser.AppUser;
import com.example.userapp.appuser.AppUserRepository;
import com.example.userapp.token.AuthToken;
import com.example.userapp.token.AuthTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthTokenRepository authTokenRepository;

    public String signIn(SignInRequest signInRequest) {

        if (appUserRepository.findByEmail(signInRequest.getEmail()).isEmpty()) {
            throw new IllegalStateException("User is not found");
        }

        AppUser user = appUserRepository.findUserByEmail(signInRequest.getEmail());

        String encodedPassword = bCryptPasswordEncoder.encode(signInRequest.getPassword());

        if (!(bCryptPasswordEncoder.matches(signInRequest.getPassword(), encodedPassword))) {
            throw new IllegalStateException("wrong password");
        }

        AuthToken token = authTokenRepository.findByAppUser(user);
        return token.getToken();

    }
}
