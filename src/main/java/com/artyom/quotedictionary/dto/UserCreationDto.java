package com.artyom.quotedictionary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserCreationDto {
    private final String username;
    private final String email;
    private final String password;

    public UserCreationDto(@JsonProperty("username") String username,
                         @JsonProperty("email") String email,
                         @JsonProperty("password") String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
