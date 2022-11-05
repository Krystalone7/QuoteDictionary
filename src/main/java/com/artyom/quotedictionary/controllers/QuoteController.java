package com.artyom.quotedictionary.controllers;

import com.artyom.quotedictionary.dto.QuoteCreationDto;
import com.artyom.quotedictionary.entities.Quote;
import com.artyom.quotedictionary.services.QuoteService;
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

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ApiOperation("Получить все цитаты")
    @GetMapping("all")
    public ResponseEntity<List<Quote>> getAll(){
        return new ResponseEntity<>(quoteService.getAll(), HttpStatus.OK);
    }

    @ApiOperation("Добавить цитату")
    @PostMapping("add")
    public ResponseEntity<Quote> addQuote(@RequestBody QuoteCreationDto quoteCreationDto){
        return new ResponseEntity<>(
                quoteService.addQuote(new Quote(quoteCreationDto.getText(), quoteCreationDto.getAuthor())), HttpStatus.OK);
    }

    @ApiOperation("Поиск цитат по автору")
    @GetMapping("{author}")
    public ResponseEntity<List<Quote>> findByAuthor(@PathVariable String author){
        return new ResponseEntity<>(quoteService.findQuotesByAuthor(author), HttpStatus.OK);
    }

    @ApiOperation("Изменение цитаты по id")
    @PutMapping("{quoteId}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long quoteId, @RequestBody QuoteCreationDto quoteCreationDto){
        Quote updatedQuote = quoteService.updateQuote(
                quoteId,
                new Quote(quoteCreationDto.getText(), quoteCreationDto.getAuthor()));
        if (updatedQuote == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(updatedQuote, HttpStatus.OK);
        }
    }

    @ApiOperation("Удалить цитату по id")
    @DeleteMapping("delete/{quoteId}")
    @Transactional
    public void deleteQuote(@PathVariable Long quoteId){
        quoteService.deleteQuote(quoteId);
    }

}
