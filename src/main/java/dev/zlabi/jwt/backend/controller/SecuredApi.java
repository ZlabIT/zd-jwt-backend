package dev.zlabi.jwt.backend.controller;

import dev.zlabi.jwt.backend.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface SecuredApi {

    @GetMapping("/hello")
    public HelloResponse hello();
}
