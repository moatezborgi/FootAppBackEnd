package com.borgi.footappbackend.repositories.userrepositories;

import com.borgi.footappbackend.entities.user.UserRefrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserRefrence, Long> {

    Optional<UserRefrence> findByUserEmail(String userEmail);
    @Query("SELECT UserRef from UserRefrence UserRef where UserRef.userEmail=:userEmail")
    UserRefrence JPQL_GetUserByUserEmail(String userEmail);
}
