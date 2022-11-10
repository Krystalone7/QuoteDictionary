package com.artyom.quotedictionary.services.mappers;

import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserDto> toDtoList(List<User> users);
}

