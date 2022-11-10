package com.artyom.quotedictionary.services.mappers;

import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User model);
}
