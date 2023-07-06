package com.example.kafkaproducerservice.kafka;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventData{

    String accountId;
    String message;

}