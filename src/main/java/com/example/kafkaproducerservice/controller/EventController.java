package com.example.kafkaproducerservice.controller;

import com.example.kafkaproducerservice.kafka.EventData;
import com.example.kafkaproducerservice.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="event")
public class EventController {

    @Autowired
    private Producer producerService;

    @PostMapping
    public void sendMessage(@RequestBody EventData event) {
        producerService.addEvent(event);
    }
}