package dev.zlabi.jwt.backend.controller.impl;

import dev.zlabi.jwt.backend.controller.SecuredApi;
import dev.zlabi.jwt.backend.dto.HelloResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredApiImpl implements SecuredApi {

    @Override
    public HelloResponse hello() {
        return new HelloResponse("Hello from Authorized API request.");
    }
}
