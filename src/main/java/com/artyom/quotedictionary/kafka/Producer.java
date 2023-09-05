package com.artyom.quotedictionary.kafka;

import com.artyom.quotedictionary.dto.QuoteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplateString;

    public static final String TOPIC = "test_topic";

    public void sendEvent(final String key) throws JsonProcessingException {

        QuoteDto dto = new QuoteDto("love toyota", "tema");
        final ObjectMapper objectMapper = new ObjectMapper();
        final String data = objectMapper.writeValueAsString(dto);

        kafkaTemplateString.send(TOPIC, key, data)
                .addCallback(
                        result -> log.info("Kafka send complete"),
                        fail -> log.error("Kafka fail send"));
    }
}

