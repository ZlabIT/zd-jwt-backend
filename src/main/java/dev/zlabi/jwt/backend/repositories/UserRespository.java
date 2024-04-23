package dev.zlabi.jwt.backend.repositories;

import dev.zlabi.jwt.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
