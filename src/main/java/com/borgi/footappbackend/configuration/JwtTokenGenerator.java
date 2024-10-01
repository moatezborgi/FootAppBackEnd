package com.borgi.footappbackend.configuration;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenGenerator {
    private final JwtEncoder jwtEncoder;

    public String generateAccessToken(Authentication authentication) {

        log.info("[JwtTokenGenerator:generateAccessToken] Token Creation Started for:{}", authentication.getName());

        Object principal = authentication.getPrincipal();

        String citizenSystemId;
        if (principal instanceof UserDetails userDetails) {
            citizenSystemId= userDetails.getUsername();
        } else if (principal instanceof String string) {
            citizenSystemId = string;
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
        }



          JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("FootAPP")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .subject(citizenSystemId)
                  .claim("role", getRoleFromAuthorities(authentication))
                  .claim("scope", getAuthorities(authentication))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    public String generateRefreshToken(Authentication authentication) {

        log.info("[JwtTokenGenerator:generateRefreshToken] Token Creation Started for:{}", authentication.getName());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("FootAPP")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1 , ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("scope", "REFRESH_TOKEN")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    // Méthode pour obtenir uniquement le rôle (ceux qui commencent par "ROLE_")
    public static String getRoleFromAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_")) // Filtrer uniquement les rôles
                .collect(Collectors.joining(" "));
    }

    // Méthode pour obtenir toutes les autorités (scopes)
    public static List<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
