package com.example.userapp.userDelete;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/delete-user/{id}")
    public String userDelete( @PathVariable ("id") Long id) {
        userDeleteService.userDelete(id);
        return "User deleted successfully";
    }
}
