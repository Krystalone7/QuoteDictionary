package com.artyom.quotedictionary.services;

import com.artyom.quotedictionary.controllers.advice.exceptions.QuoteNotFoundException;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.repo.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getAll(){
        return quoteRepository.findAll();
    }

    public Quote addQuote(Quote quote){
        return quoteRepository.saveAndFlush(quote);
    }

    public List<Quote> findQuotesByAuthor(String author){
        return quoteRepository.findQuotesByAuthor(author);
    }

    public void deleteQuote(Long id){
        quoteRepository.deleteQuoteById(id);
    }

    public Quote updateQuote(Long quoteId, Quote quote){
        Quote existingQuote = quoteRepository.findQuoteById(quoteId).orElse(null);
        if (existingQuote != null){
            existingQuote.setAuthor(quote.getAuthor());
            existingQuote.setText(quote.getText());
            return quoteRepository.saveAndFlush(existingQuote);
        } else{
            throw new QuoteNotFoundException(quoteId.toString());
        }
    }
}
