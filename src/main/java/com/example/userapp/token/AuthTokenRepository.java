package com.example.userapp.token;

import com.example.userapp.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    AuthToken findByAppUser(AppUser user);

    @Query("select a from AuthToken a where a.appUser.id = :id")
    AuthToken findTokenByAppUserId(Long id);


 }
