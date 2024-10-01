package com.borgi.footappbackend.repositories.userrepositories;

import com.borgi.footappbackend.entities.user.UserRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolePermissionRepository extends JpaRepository<UserRolePermission, Long> {
}
