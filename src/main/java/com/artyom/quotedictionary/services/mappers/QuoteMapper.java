package com.artyom.quotedictionary.services.mappers;

import com.artyom.quotedictionary.dto.QuoteDto;
import com.artyom.quotedictionary.entities.Quote;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuoteMapper {

    public Quote mapToQuote(QuoteDto quoteDto){
        return new Quote(quoteDto.getText(), quoteDto.getAuthor());
    }

    public QuoteDto mapToDto(Quote quote){
        return new QuoteDto(quote.getText(), quote.getAuthor());
    }

    public List<QuoteDto> mapToDtoList(List<Quote> quotes){
        return quotes.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
