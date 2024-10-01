package com.borgi.footappbackend.configuration;


import com.borgi.footappbackend.entities.user.UserRefrence;
import com.borgi.footappbackend.entities.user.UserRole;
import com.borgi.footappbackend.entities.user.UserRolePermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class UserInfoConfig  implements UserDetails {
    private final UserRefrence userRefrence;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Ajouter les autorisations directes de l'utilisateur
        if (this.userRefrence.getUserRole() != null) {
            for (UserRolePermission userRolePermission : this.userRefrence.getUserRole().getUserRolePermissionList()) {
                if (userRolePermission.getUserRolePermissionName() != null && userRolePermission.isActiveUserRolePermission() ) {
                    authorities.add(new SimpleGrantedAuthority(userRolePermission.getUserRolePermissionName()));
                }
            }
        }
        assert userRefrence.getUserRole() != null;
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ userRefrence.getUserRole().getUserRoleName()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return userRefrence.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userRefrence.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userRefrence.isActiveAccount();
    }
}
