package com.artyom.quotedictionary.kafka;

import com.artyom.quotedictionary.dto.QuoteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = "test_topic", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenGroupTopic2(String message) {

        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final QuoteDto dto = objectMapper.readValue(message, QuoteDto.class);
            System.out.println(dto.getAuthor() + " " + dto.getText());

        } catch (JsonProcessingException e) {
            log.error("Couldn't parse message: {}; exception: ", message, e);
        }
    }
}

