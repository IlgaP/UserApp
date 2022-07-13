package com.example.userapp.appuser;

import com.example.userapp.api.RegistrationRequest;
import com.example.userapp.api.SignInRequest;
import com.example.userapp.avatar.Avatar;
import com.example.userapp.avatar.AvatarRepository;
import com.example.userapp.token.AuthToken;
import com.example.userapp.token.AuthTokenRepository;
import com.example.userapp.token.AuthTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthTokenService authTokenService;
    private final AuthTokenRepository authTokenRepository;
    private final AvatarRepository avatarRepository;
    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    public String register(RegistrationRequest registrationRequest) {

        boolean userExists = appUserRepository.findByEmail(registrationRequest.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("User with this email already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(registrationRequest.getPassword());

        AppUser appUser = new AppUser(registrationRequest.getEmail(), encodedPassword, registrationRequest.getAppUserRole());

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        AuthToken authToken = new AuthToken(
                token,
                appUser
        );

        authTokenService.saveAuthToken(authToken);

        return token;
    }

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

    public void userDelete(Long id) {

        AuthToken authToken = authTokenRepository.findTokenByAppUserId(id);
        Long tokenId = authToken.getId();
        authTokenRepository.deleteById(tokenId);

        Optional<Avatar> avatar = avatarRepository.findAvatarByAppUserId(id);
        if (avatar.isPresent()) {
            Long avatarId = avatar.get().getId();
            avatarRepository.deleteById(avatarId);
        }

        appUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
