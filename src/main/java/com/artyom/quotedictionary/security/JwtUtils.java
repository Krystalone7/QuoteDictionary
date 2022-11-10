package com.artyom.quotedictionary.security;

import com.artyom.quotedictionary.entities.Role;
import com.artyom.quotedictionary.security.context.AuthContextImpl;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static AuthContextImpl generate(Claims claims){
        AuthContextImpl authContext = new AuthContextImpl();
        authContext.setEmail(claims.getSubject());
        authContext.setRoles(getRoles(claims));
        return authContext;
    }

    private static Set<Role> getRoles(Claims claims){
        List<String> roles = claims.get("roles", List.class);
        return roles.stream().map(Role::new).collect(Collectors.toSet());
    }
}
