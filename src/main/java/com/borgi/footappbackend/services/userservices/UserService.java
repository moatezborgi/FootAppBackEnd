package com.borgi.footappbackend.services.userservices;


import com.borgi.footappbackend.configuration.JwtTokenGenerator;
import com.borgi.footappbackend.configuration.JwtTokenUtils;
import com.borgi.footappbackend.dto.userdto.UserAuthenticationRequest;
import com.borgi.footappbackend.dto.userdto.UserAuthenticationResponse;
import com.borgi.footappbackend.entities.user.*;
import com.borgi.footappbackend.repositories.userrepositories.UserRefreshTokenRepository;
import com.borgi.footappbackend.repositories.userrepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;

    private final JwtTokenUtils jwtTokenUtils;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final PasswordEncoder passwordEncoder; // Ajouter un PasswordEncoder

    private static Authentication createAuthenticationObject(UserRefrence userRefrence) {
        // récuperer les détails de l'utilisateur
        String username = userRefrence.getUserEmail();
        String password = userRefrence.getUserPassword();
        UserRole roles = userRefrence.getUserRole();

        Set<GrantedAuthority> authorities = new HashSet<>();
        // Ajouter les autorisations directes de l'utilisateur
        if (userRefrence.getUserRole() != null) {
            for (UserRolePermission userRolePermission : userRefrence.getUserRole().getUserRolePermissionList()) {
                if (userRolePermission.getUserRolePermissionName() != null && userRolePermission.isActiveUserRolePermission() ) {
                    authorities.add(new SimpleGrantedAuthority(userRolePermission.getUserRolePermissionName()));
                }
            }
        }
        assert userRefrence.getUserRole() != null;
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ userRefrence.getUserRole().getUserRoleName()));


        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public UserAuthenticationResponse userAuthentication(UserAuthenticationRequest userAuthenticationRequest) {
        var  userRefrence=userRepository.findByUserEmail(userAuthenticationRequest.getUserEmail());
        if (userRefrence.isEmpty()) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + userAuthenticationRequest.getUserEmail());
        }
        // Vérifier si le mot de passe est correct
        if (!passwordEncoder.matches(userAuthenticationRequest.getPassword(), userRefrence.get().getUserPassword())) {
            throw new IllegalArgumentException("Veuillez vérifier votre email ou mot de passe");
        }
        Authentication authentication=createAuthenticationObject(userRefrence.get());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtTokenGenerator.generateAccessToken(authentication); // null pour structId
        String refreshToken = jwtTokenGenerator.generateRefreshToken(authentication);
        int accessTokenExpiry = 3600; // Exemple : 1 heure

        saveUserRefreshToken(userRefrence.get(), refreshToken);
        return UserAuthenticationResponse.builder()
                .userEmail(userAuthenticationRequest.getUserEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.Bearer)
                .userFullName(userRefrence.get().getUserFirstName()+" "+userRefrence.get().getUserLastName())
                .accessTokenExpiry(accessTokenExpiry)
                .build();
    }

    private void saveUserRefreshToken(UserRefrence userInfoEntity, String refreshToken) {
        userRefreshTokenRepository.updateRefreshTokensForUser(userInfoEntity);
        var refreshTokenEntity = UserRefreshToken.builder()
                .userRefrence(userInfoEntity)
                .refreshToken(refreshToken)
                .revoked(false)
                .build();
        userRefreshTokenRepository.save(refreshTokenEntity);
    }
}
