package com.artyom.quotedictionary.controllers;

import com.artyom.quotedictionary.dto.UserCreationDto;
import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.security.CookieToken;

import com.artyom.quotedictionary.security.data.Authorization;
import com.artyom.quotedictionary.security.data.Token;
import com.artyom.quotedictionary.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService auth;
    private final CookieToken cookieToken;

    @PostMapping("/registration")
    public UserDto createUser(UserCreationDto userCreationDto){ return auth.registration(userCreationDto);}

    @PostMapping("/auth")
    public String signIn(HttpServletResponse response, Authorization authorization) {
        Token token = auth.signIn(authorization);
        cookieToken.createCookieToken(response, token.getToken());

        return "Вы успешно вошли в систему!";
    }
}
