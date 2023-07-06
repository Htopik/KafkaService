package com.example.kafkaconsumerservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;


public class Consumer {

    @KafkaListener(topics = "intercessor", containerFactory = "kafkaListenerContainerFactoryString", groupId = "${group1}")
    public void listenGroupTopic(String message) {
        System.out.println("Receive message from 1: " + message);

    }
}