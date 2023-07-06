package com.example.kafkaproducerservice.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplateString;
    public void addEvent(EventData event){
        kafkaTemplateString.send("intercessor", event.getAccountId(), event.getMessage());
    }
}
    