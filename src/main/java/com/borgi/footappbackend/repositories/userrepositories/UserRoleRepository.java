package com.borgi.footappbackend.repositories.userrepositories;

import com.borgi.footappbackend.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Number> {
}
