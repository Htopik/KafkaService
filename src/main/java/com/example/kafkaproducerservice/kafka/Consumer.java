package com.example.kafkaproducerservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;


public class Consumer {

    @KafkaListener(topics = "intercessor", containerFactory = "kafkaListenerContainerFactoryString", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupTopic(String message) {
        System.out.println("Receive message from 1: " + message);

    }
    @KafkaListener(topics = "intercessor", containerFactory = "kafkaListenerContainerFactoryString", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupTopic2(String message) {
        System.out.println("Receive message from 2: " + message);

    }
}