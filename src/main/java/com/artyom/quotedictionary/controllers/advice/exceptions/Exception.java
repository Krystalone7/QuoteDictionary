package com.artyom.quotedictionary.controllers.advice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Exception {
    private String code;
    private String description;
}
