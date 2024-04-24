package dev.zlabi.jwt.backend.controller;

import dev.zlabi.jwt.backend.dto.LoginRequest;
import dev.zlabi.jwt.backend.dto.LoginResponse;
import dev.zlabi.jwt.backend.dto.SignupRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws Exception;

    @PostMapping("/signup")
    ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest);
}
