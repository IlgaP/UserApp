package com.example.userapp.token;

import com.example.userapp.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    AuthToken findByAppUser(AppUser user);

 }
