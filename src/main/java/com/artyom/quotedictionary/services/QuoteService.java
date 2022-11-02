package com.artyom.quotedictionary.services;

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
}
