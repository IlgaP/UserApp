package com.example.userapp.avatar;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class AvatarController {

    private final AvatarService avatarService;

    @PostMapping("/upload-avatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("file")MultipartFile file) throws IOException {
        return avatarService.uploadAvatar(file);
    }

    @GetMapping("/get-avatar/{id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        return avatarService.getAvatar(id);
    }
}
