package com.example.userapp.appuser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @GetMapping({"", "/welcome"})
    public String welcome() {
        return "Welcome";
    }
}
