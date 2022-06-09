package com.example.userapp.registration;

import com.example.userapp.appuser.AppUser;
import com.example.userapp.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

     private final AppUserService appUserService;

    public String register(RegistrationRequest registrationRequest) {

        return appUserService.signUpUser(
                new AppUser(
                        registrationRequest.getEmail(),
                        registrationRequest.getPassword()
                )
        );
    }

}
