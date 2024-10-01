package com.borgi.footappbackend.configuration;


import com.borgi.footappbackend.repositories.userrepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerInfo implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUserEmail(username)
                .map(UserInfoConfig::new)
                .orElseThrow(()-> new UsernameNotFoundException("utilisateur avec email: "+username+"n'existe pas"));
    }
}
