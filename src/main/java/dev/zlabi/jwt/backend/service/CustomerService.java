package dev.zlabi.jwt.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerService extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
