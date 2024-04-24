package dev.zlabi.jwt.backend.service;

import dev.zlabi.jwt.backend.domain.Customer;
import dev.zlabi.jwt.backend.dto.SignupRequest;

public interface AuthService {
    Customer createCustomer(SignupRequest signupRequest);
}