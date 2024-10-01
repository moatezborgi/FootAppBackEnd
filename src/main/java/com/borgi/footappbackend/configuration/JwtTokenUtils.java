package com.borgi.footappbackend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    private final RSAKeyRecord rsaKeyRecord;

    public String getUserName(Jwt jwtToken){
        return jwtToken.getSubject();
    }

    public boolean isTokenValid(Jwt jwtToken, UserDetails userDetails){
        final String userName = getUserName(jwtToken);
        boolean isTokenExpired = getIfTokenIsExpired(jwtToken);
        boolean isTokenUserSameAsDatabase = userName.equals(userDetails.getUsername());

        return !isTokenExpired  && isTokenUserSameAsDatabase;

    }

    private boolean getIfTokenIsExpired(Jwt jwtToken) {
        return Objects.requireNonNull(jwtToken.getExpiresAt()).isBefore(Instant.now());
    }

    public String getUserNameOfUser(String jwtTokenString) {
        JwtDecoder jwtDecoder =  NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();
        final Jwt jwtToken = jwtDecoder.decode(jwtTokenString);

        return getUserName(jwtToken);
    }
    // Vérifier si le token contient le scope requis
    public boolean isScopeValid(Jwt jwtToken, String requiredScope) {
        List<String> scopes = jwtToken.getClaimAsStringList("scope");
        return scopes != null && scopes.contains(requiredScope);
    }
}
