package com.company.orders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaSend {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${kafka.activated}")
    private int kafkaState;

    public void sendMessage(String msg) {
        if (kafkaState == 1) {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Mensaje enviado=[" + msg + "] offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Mensaje no enviado=[" + msg + "] motivo : " + ex.getMessage());
                }
            });
        } else {
            System.out.println("Kafka desactivado");
        }
    }
}
