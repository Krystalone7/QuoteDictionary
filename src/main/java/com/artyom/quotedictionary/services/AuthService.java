package com.artyom.quotedictionary.services;

import com.artyom.quotedictionary.dto.UserCreationDto;
import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.entities.Role;
import com.artyom.quotedictionary.entities.User;
import com.artyom.quotedictionary.repo.RoleRepository;
import com.artyom.quotedictionary.repo.UserRepository;
import com.artyom.quotedictionary.security.JwtActions;
import com.artyom.quotedictionary.security.context.UserContext;
import com.artyom.quotedictionary.security.data.Authorization;
import com.artyom.quotedictionary.security.data.Token;
import com.artyom.quotedictionary.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtActions jwtActions;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    public Token signIn(Authorization authorization) {
        User user = userRepository.findByUsername(authorization.getUsername()).orElseThrow();

        UserContext userContext = new UserContext(
                user.getUsername(),
                user.getPassword(),
                user.getRoles());

        if (bCryptPasswordEncoder.matches(authorization.getPassword(), user.getPassword())){
            return new Token(jwtActions.createToken(userContext));
        } else{
            throw new RuntimeException("Аутентификация не пройдена!");
        }
    }

    public UserDto registration(UserCreationDto userCreationDto) {
        Role role = roleRepository.getById(2L);
        User user = new User(
                userCreationDto.getUsername(),
                userCreationDto.getEmail(),
                bCryptPasswordEncoder.encode(userCreationDto.getPassword())
        );
        user.setRoles(Collections.singleton(role));
        user = userRepository.saveAndFlush(user);
        return userMapper.toDto(user);
    }
}
