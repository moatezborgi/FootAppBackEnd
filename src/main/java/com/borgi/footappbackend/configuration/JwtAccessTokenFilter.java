package com.borgi.footappbackend.configuration;

import com.borgi.footappbackend.entities.user.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAccessTokenFilter extends OncePerRequestFilter {
    private final RSAKeyRecord rsaKeyRecord;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserManagerInfo userManagerInfo;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Ajoutez les URLs que vous souhaitez exclure du filtrage
        String requestURI = request.getRequestURI();
        return requestURI.startsWith("/Team/getAllTeams") ||
                requestURI.startsWith("/League/getAllLeagues") ||
                requestURI.startsWith("/Player/getAllPlayers");
    }

     @Override
     protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {

         final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

         if (authHeader == null || !authHeader.startsWith(TokenType.Bearer.name())) {
             filterChain.doFilter(request, response);
             return;
         }

         final String token = authHeader.substring(7);
         try {
             JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();
             final Jwt jwtToken = jwtDecoder.decode(token);

             // Extraire le nom d'utilisateur
             final String userName = jwtTokenUtils.getUserName(jwtToken);
             log.info("[JwtAccessTokenFilter:doFilterInternal] Username from token: {}", userName);

             // Vérifier les scopes : Gestion des joueurs et Gestion des leagues
             boolean hasPlayerManagementScope = jwtTokenUtils.isScopeValid(jwtToken, "Gestion des joueurs");
             boolean hasLeagueManagementScope = jwtTokenUtils.isScopeValid(jwtToken, "Gestion des leagues");

             // Autoriser si au moins un des scopes est valide
             if (hasPlayerManagementScope || hasLeagueManagementScope) {
                 log.info("[JwtAccessTokenFilter:doFilterInternal] User has the required scope.");
                 filterChain.doFilter(request, response);
             } else {
                 log.error("[JwtAccessTokenFilter:doFilterInternal] User does not have the required scope.");
                 response.setStatus(HttpStatus.FORBIDDEN.value());
                 response.getWriter().write("User does not have the required scope.");
                 return;
             }

         } catch (JwtValidationException ex) {
             log.error("[JwtAccessTokenFilter:doFilterInternal] Token validation error: {}", ex.getMessage());
             response.setStatus(HttpStatus.UNAUTHORIZED.value());
             response.getWriter().write("Invalid token.");
             return;
         }
     }
}