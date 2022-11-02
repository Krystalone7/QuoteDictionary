package com.artyom.quotedictionary.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class QuoteCreationDto {

    private final String text;
    private final String author;

    @JsonCreator
    public QuoteCreationDto(@JsonProperty("text") String text,
                            @JsonProperty("author") String author){
        this.text = text;
        this.author = author;
    }

}
