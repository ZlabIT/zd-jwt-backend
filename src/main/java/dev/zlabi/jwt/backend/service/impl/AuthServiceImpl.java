package dev.zlabi.jwt.backend.service.impl;


import dev.zlabi.jwt.backend.domain.Customer;
import dev.zlabi.jwt.backend.dto.SignupRequest;
import dev.zlabi.jwt.backend.repository.CustomerRepository;
import dev.zlabi.jwt.backend.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Customer createCustomer(SignupRequest signupRequest) {
        //Check if customer already exist
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest, customer);

        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);
        Customer createdCustomer = customerRepository.save(customer);
        customer.setId(createdCustomer.getId());

        return customer;
    }
}