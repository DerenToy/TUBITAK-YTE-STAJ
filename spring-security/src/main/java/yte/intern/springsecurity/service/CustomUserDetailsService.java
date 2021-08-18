package yte.intern.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yte.intern.springsecurity.domain.CustomUser;
import yte.intern.springsecurity.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

// kulllanıcıyı almalı

    private final UserRepository customUserRepository;

    public CustomUserDetailsService(final UserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı!"));
    }
}
