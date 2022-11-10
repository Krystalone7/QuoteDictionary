package com.artyom.quotedictionary.services;

import com.artyom.quotedictionary.dto.UserCreationDto;
import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.entities.User;
import com.artyom.quotedictionary.repo.UserRepository;
import com.artyom.quotedictionary.services.mappers.UserListMapper;
import com.artyom.quotedictionary.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserListMapper userListMapper;

    public UserDto createUser(UserCreationDto userCreationDto) {
        User user = new User(
                userCreationDto.getEmail(),
                userCreationDto.getPassword()
        );

        user = userRepository.saveAndFlush(user);

        return userMapper.toDto(user);
    }
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return userListMapper.toDtoList(users);
    }
}
