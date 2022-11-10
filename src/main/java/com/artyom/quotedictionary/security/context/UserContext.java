package com.artyom.quotedictionary.security.context;

import com.artyom.quotedictionary.entities.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UserContext {
    private final String email;
    private final String password;
    private final Set<Role> roles;
}
