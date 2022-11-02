package com.artyom.quotedictionary.repo;

import com.artyom.quotedictionary.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findQuotesByAuthor(String author);

    void deleteQuoteById(Long id);
}
