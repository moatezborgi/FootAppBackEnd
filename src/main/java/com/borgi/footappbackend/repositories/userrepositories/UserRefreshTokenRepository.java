package com.borgi.footappbackend.repositories.userrepositories;

import com.borgi.footappbackend.entities.user.UserRefrence;
import com.borgi.footappbackend.entities.user.UserRefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    UserRefreshToken findByRefreshToken(String refreshToken);
    @Modifying
    @Transactional
    @Query("UPDATE UserRefreshToken crf SET crf.revoked = true WHERE crf.userRefrence = :userRefrence")
    void updateRefreshTokensForUser(UserRefrence userRefrence);
}
