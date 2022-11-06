package com.artyom.quotedictionary.services;


import com.artyom.quotedictionary.controllers.advice.exceptions.QuoteNotFoundException;
import com.artyom.quotedictionary.dto.QuoteDto;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.repo.QuoteRepository;
import com.artyom.quotedictionary.services.mappers.QuoteMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class QuotedServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @Spy
    private QuoteMapper quoteMapper;
    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void addQuoteTest(){
        QuoteDto quoteDto = new QuoteDto("Test quote", "MegaAuthor");
        when(quoteRepository.saveAndFlush(any(Quote.class))).thenReturn(null);
        QuoteDto addedQuote = quoteService.addQuote(quoteDto);
        Assertions.assertEquals(quoteDto.getAuthor(), addedQuote.getAuthor());
        Assertions.assertEquals(quoteDto.getText(), addedQuote.getText());
    }

    @Test
    public void getAllQuotesTest(){
        List<Quote> quotes = Arrays.asList(
                new Quote("Test quote 1", "MegaAuthor"),
                new Quote("Test quote 2", "SuperAuthor"),
                new Quote("Test quote 3", "CyberAuthor")
        );
        List<QuoteDto> quoteDtos = Arrays.asList(
                new QuoteDto("Test quote 1", "MegaAuthor"),
                new QuoteDto("Test quote 2", "SuperAuthor"),
                new QuoteDto("Test quote 3", "CyberAuthor")
        );
        when(quoteRepository.findAll()).thenReturn(quotes);
        List<QuoteDto> allQuotes = quoteService.getAll();

        Assertions.assertEquals(quoteDtos.size(), allQuotes.size());
        for (int i = 0; i < allQuotes.size(); i++){
            Assertions.assertEquals(quoteDtos.get(i).getAuthor(), allQuotes.get(i).getAuthor());
            Assertions.assertEquals(quoteDtos.get(i).getText(), allQuotes.get(i).getText());
        }
    }

    @Test
    public void updateNotFoundExceptionTest(){
        Long id = 100L;
        QuoteDto quoteDto = new QuoteDto("New text", "New Author");
        when(quoteRepository.findQuoteById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(QuoteNotFoundException.class, () -> {
            quoteService.updateQuote(id, quoteDto);
        });
    }

}
