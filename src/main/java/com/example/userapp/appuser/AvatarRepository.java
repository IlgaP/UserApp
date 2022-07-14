package com.example.userapp.appuser;

import com.example.userapp.api.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    @Query("select a from Avatar a where a.appUser.id = :id")
    Optional<Avatar> findAvatarByAppUserId(Long id);
}
