package com.artyom.quotedictionary.services;

import com.artyom.quotedictionary.controllers.advice.exceptions.QuoteNotFoundException;
import com.artyom.quotedictionary.dto.QuoteDto;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.repo.QuoteRepository;
import com.artyom.quotedictionary.services.mappers.QuoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;

    private final QuoteMapper quoteMapper;

    public List<QuoteDto> getAll(){
        return quoteMapper.mapToDtoList(quoteRepository.findAll());
    }

    public QuoteDto addQuote(QuoteDto quoteDto){
        Quote quote = quoteMapper.mapToQuote(quoteDto);
        quoteRepository.saveAndFlush(quote);
        return quoteMapper.mapToDto(quote);
    }

    public List<QuoteDto> findQuotesByAuthor(String author){
        return quoteMapper.mapToDtoList(quoteRepository.findQuotesByAuthor(author));
    }

    public void deleteQuote(Long id){
        quoteRepository.deleteQuoteById(id);
    }

    public QuoteDto updateQuote(Long quoteId, QuoteDto quoteDto){
        Quote existingQuote = quoteRepository.findQuoteById(quoteId).orElseThrow(() -> new QuoteNotFoundException(quoteId.toString()));
        existingQuote.setAuthor(quoteDto.getAuthor());
        existingQuote.setText(quoteDto.getText());
        return quoteMapper.mapToDto(quoteRepository.saveAndFlush(existingQuote));
    }
}
