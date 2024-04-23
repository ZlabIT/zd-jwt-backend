package dev.zlabi.jwt.backend.services;

import dev.zlabi.jwt.backend.dto.CredentialsDto;
import dev.zlabi.jwt.backend.dto.UserDto;
import dev.zlabi.jwt.backend.entities.User;
import dev.zlabi.jwt.backend.exceptions.AppException;
import dev.zlabi.jwt.backend.mapper.UserMapper;
import dev.zlabi.jwt.backend.repositories.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRespository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);

    }

}
