package com.borgi.footappbackend.configuration;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final  UserManagerInfo userManagerInfo;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Order(1)
    @Bean
    public SecurityFilterChain userAuthenticationapiSecurityFilterChain(HttpSecurity httpSecurity, RSAKeyRecord rSAKeyRecord, JwtTokenUtils jwtTokenUtils) throws Exception{
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/Authentication/User/**"))
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Authentication/User/userAuthentication/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userManagerInfo)

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Order(2)
    @Bean
    public SecurityFilterChain leagueapiSecurityFilterChain(HttpSecurity httpSecurity, RSAKeyRecord rSAKeyRecord, JwtTokenUtils jwtTokenUtils) throws Exception{
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/League/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/League/addLeague/**").permitAll()
                        .requestMatchers("/League/getAllLeagues/**").permitAll()
                                .requestMatchers("/League/updateLeague/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userManagerInfo)

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
    @Order(3)
    @Bean
    public SecurityFilterChain settingsapiSecurityFilterChain(HttpSecurity httpSecurity, RSAKeyRecord rSAKeyRecord, JwtTokenUtils jwtTokenUtils) throws Exception{
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/Settings/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Settings/getAllNationality/**").permitAll()
                                .requestMatchers("/Settings/getAllPosition/**").permitAll()



                        .anyRequest().authenticated())
                .userDetailsService(userManagerInfo)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .build();
    }


    @Order(4)
    @Bean
    public SecurityFilterChain teamapiSecurityFilterChain(HttpSecurity httpSecurity, RSAKeyRecord rSAKeyRecord, JwtTokenUtils jwtTokenUtils) throws Exception {
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/Team/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Team/addTeam/**").permitAll()
                        .requestMatchers("/Team/getAllTeams/**").permitAll()
                        .requestMatchers("/Team/updateTeam/**").permitAll()


                        .anyRequest().authenticated())
                .userDetailsService(userManagerInfo)

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

    @Order(5)
    @Bean
    public SecurityFilterChain playerpiSecurityFilterChain(HttpSecurity httpSecurity, RSAKeyRecord rSAKeyRecord, JwtTokenUtils jwtTokenUtils) throws Exception{
        return httpSecurity
                .securityMatcher(new AntPathRequestMatcher("/Player/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Player/addPlayer/**").permitAll()
                                .requestMatchers("/Player/getAllPlayers/**").permitAll()
                                .requestMatchers("/Player/updatePlayer/**").permitAll()
                                .requestMatchers("/Player/updatePlayerMarketValue/**").permitAll()
                                .requestMatchers("/Player/updatePlayerContract/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userManagerInfo)

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .httpBasic(withDefaults())
                .build();
    }


}
