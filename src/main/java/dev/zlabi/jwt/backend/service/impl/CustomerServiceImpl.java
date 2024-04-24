package dev.zlabi.jwt.backend.service.impl;


import dev.zlabi.jwt.backend.domain.Customer;
import dev.zlabi.jwt.backend.repository.CustomerRepository;
import dev.zlabi.jwt.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Write logic to fetch customer from DB
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email" + email));

        return new User(customer.getEmail(), customer.getPassword(), Collections.emptyList());
    }
}