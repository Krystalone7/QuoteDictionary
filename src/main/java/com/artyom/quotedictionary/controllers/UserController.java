package com.artyom.quotedictionary.controllers;


import com.artyom.quotedictionary.dto.UserDto;
import com.artyom.quotedictionary.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/get-all")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }
}