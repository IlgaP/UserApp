package com.example.userapp.userDelete;

import com.example.userapp.appuser.AppUserRepository;
import com.example.userapp.avatar.Avatar;
import com.example.userapp.avatar.AvatarRepository;
import com.example.userapp.token.AuthToken;
import com.example.userapp.token.AuthTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserDeleteService {

    private final AppUserRepository appUserRepository;
    private final AuthTokenRepository authTokenRepository;
    private final AvatarRepository avatarRepository;

    public void userDelete(Long id) {

        AuthToken authToken = authTokenRepository.findTokenByAppUserId(id);
        Long tokenId = authToken.getId();
        authTokenRepository.deleteById(tokenId);

        Optional <Avatar> avatar = avatarRepository.findAvatarByAppUserId(id);
        if (avatar.isPresent()) {
            Long avatarId = avatar.get().getId();
            avatarRepository.deleteById(avatarId);
        }

        appUserRepository.deleteById(id);
    }
}
