package com.example.userapp.avatar;

import com.example.userapp.appuser.AppUser;
import com.example.userapp.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final AppUserRepository appUserRepository;

    public String uploadAvatar(MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        AppUser appUser = appUserRepository.findUserByEmail(userEmail);

        Avatar avatar = new Avatar(file.getBytes(), appUser);

        avatarRepository.save(avatar);
        Long id = avatar.getId();

        return "/get-avatar/" + id;
    }

    public ResponseEntity<byte[]> getAvatar(Long id) {
        Optional<Avatar> avatar = avatarRepository.findById(id);
        byte[] file;
        if (avatar.isPresent()) {
            file = avatar.get().getFile();
        } else {
            throw new IllegalStateException("Avatar not found!");
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }
}
