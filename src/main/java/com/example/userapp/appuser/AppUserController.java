package com.example.userapp.appuser;

import com.example.userapp.api.RegistrationRequest;
import com.example.userapp.api.SignInRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @PostMapping("/registration")
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return appUserService.register(registrationRequest);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody SignInRequest signInRequest) {

        return appUserService.signIn(signInRequest);
    }

    @DeleteMapping("/delete-user/{id}")
    public String userDelete( @PathVariable("id") Long id) {
        appUserService.userDelete(id);
        return "User deleted successfully";
    }
}
