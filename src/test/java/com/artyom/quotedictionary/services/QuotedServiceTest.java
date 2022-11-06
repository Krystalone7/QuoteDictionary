package com.artyom.quotedictionary.services;


import com.artyom.quotedictionary.controllers.advice.exceptions.QuoteNotFoundException;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.repo.QuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class QuotedServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void addQuoteTest(){
        Quote quote = new Quote(1L, "Test quote", "MegaAuthor");
        when(quoteRepository.saveAndFlush(quote)).thenReturn(quote);
        Quote addedQuote = quoteService.addQuote(quote);
        Assertions.assertEquals(quote.getAuthor(), addedQuote.getAuthor());
        Assertions.assertEquals(quote.getText(), addedQuote.getText());
    }

    @Test
    public void getAllQuotesTest(){
        List<Quote> quotes = Arrays.asList(
                new Quote(1L, "Test quote 1", "MegaAuthor"),
                new Quote(2L, "Test quote 2", "SuperAuthor"),
                new Quote(3L, "Test quote 3", "CyberAuthor")
        );
        when(quoteRepository.findAll()).thenReturn(quotes);
        List<Quote> allQuotes = quoteService.getAll();
        Assertions.assertEquals(quotes, allQuotes);
    }

    @Test
    public void updateNotFoundExceptionTest(){
        Long id = 100L;
        Quote quote = new Quote("New text", "New Author");
        when(quoteRepository.findQuoteById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(QuoteNotFoundException.class, () -> {
            quoteService.updateQuote(id, quote);
        });
    }

}
