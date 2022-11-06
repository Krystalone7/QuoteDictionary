package com.artyom.quotedictionary.controllers.advice;

import com.artyom.quotedictionary.controllers.advice.exceptions.Exception;
import com.artyom.quotedictionary.controllers.advice.exceptions.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuoteControllerAdvice {

    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Exception> handleException(QuoteNotFoundException e){
        String code = "not_found_error";
        String description = "Quote with id " + e.getMessage() + " not found";
        return new ResponseEntity<>(new Exception(code, description), HttpStatus.NOT_FOUND);
    }
}
