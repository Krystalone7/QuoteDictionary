package com.artyom.quotedictionary.controllers;

import com.artyom.quotedictionary.dto.QuoteDto;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.services.QuoteService;
import com.artyom.quotedictionary.services.mappers.QuoteMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Api(tags = "quotes")
@RequestMapping("quote")
public class QuoteController {

    private final QuoteService quoteService;

    private final QuoteMapper quoteMapper;

    public QuoteController(QuoteService quoteService, QuoteMapper quoteMapper) {
        this.quoteService = quoteService;
        this.quoteMapper = quoteMapper;
    }

    @ApiOperation("Получить все цитаты")
    @GetMapping("all")
    public ResponseEntity<List<QuoteDto>> getAll(){
        return new ResponseEntity<>(quoteMapper.mapToDtoList(quoteService.getAll()), HttpStatus.OK);
    }

    @ApiOperation("Добавить цитату")
    @PostMapping("add")
    public ResponseEntity<QuoteDto> addQuote(@RequestBody QuoteDto quoteDto){
        return new ResponseEntity<>(
                quoteMapper.mapToDto(quoteService.addQuote(quoteMapper.mapToQuote(quoteDto))), HttpStatus.OK);
    }

    @ApiOperation("Поиск цитат по автору")
    @GetMapping("{author}")
    public ResponseEntity<List<QuoteDto>> findByAuthor(@PathVariable String author){
        return new ResponseEntity<>(quoteMapper.mapToDtoList(quoteService.findQuotesByAuthor(author)), HttpStatus.OK);
    }

    @ApiOperation("Изменение цитаты по id")
    @PutMapping("{quoteId}")
    public ResponseEntity<QuoteDto> updateQuote(@PathVariable Long quoteId, @RequestBody QuoteDto quoteDto){
        Quote updatedQuote = quoteService.updateQuote(
                quoteId,
                quoteMapper.mapToQuote(quoteDto));
        return new ResponseEntity<>(quoteMapper.mapToDto(updatedQuote), HttpStatus.OK);
    }

    @ApiOperation("Удалить цитату по id")
    @DeleteMapping("delete/{quoteId}")
    @Transactional
    public void deleteQuote(@PathVariable Long quoteId){
        quoteService.deleteQuote(quoteId);
    }

}
