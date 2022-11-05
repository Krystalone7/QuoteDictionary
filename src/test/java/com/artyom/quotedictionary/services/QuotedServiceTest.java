package com.artyom.quotedictionary.services;


import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.repo.QuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.lenient;
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
        Assertions.assertEquals(quote, addedQuote);
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

    @ParameterizedTest
    @MethodSource("quotes")
    public void updateQuoteTest(Long quoteId, Quote newQuote, Quote oldQuote, Quote expectedQuote){

        when(quoteRepository.findById(quoteId)).thenReturn(Optional.ofNullable(oldQuote));
        lenient().when(quoteRepository.saveAndFlush(newQuote)).thenReturn(newQuote);

        Quote actualQuote = quoteService.updateQuote(quoteId, newQuote);
        Assertions.assertEquals(expectedQuote, actualQuote);

    }

    private static Stream<Arguments> quotes(){
        return Stream.of(
                Arguments.of(
                        1L,
                        new Quote("Updated text", "Updated author"),
                        new Quote("Old text", "Old author"),
                        new Quote("Updated text", "Updated author")),
                Arguments.of(
                        10L,
                        new Quote("Updated text", "Updated author"),
                        null,
                        null)
        );
    }
}
