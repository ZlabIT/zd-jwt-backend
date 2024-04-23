package dev.zlabi.jwt.backend.mapper;

import dev.zlabi.jwt.backend.dto.UserDto;
import dev.zlabi.jwt.backend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
