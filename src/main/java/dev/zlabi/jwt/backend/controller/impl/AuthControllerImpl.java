package dev.zlabi.jwt.backend.controller.impl;

import dev.zlabi.jwt.backend.controller.AuthController;
import dev.zlabi.jwt.backend.domain.Customer;
import dev.zlabi.jwt.backend.dto.LoginRequest;
import dev.zlabi.jwt.backend.dto.LoginResponse;
import dev.zlabi.jwt.backend.dto.SignupRequest;
import dev.zlabi.jwt.backend.service.AuthService;
import dev.zlabi.jwt.backend.service.CustomerService;
import dev.zlabi.jwt.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;


    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException bce) {
            throw new BadCredentialsException("Incorrect mail or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }
        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new LoginResponse(jwt);
    }

    @Override
    public ResponseEntity<?> signupCustomer(SignupRequest signupRequest) {
        Customer createdCustomer = authService.createCustomer(signupRequest);

        if (createdCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
    }
}
