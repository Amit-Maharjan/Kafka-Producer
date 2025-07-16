package com.maharjan.kafka.services;

import com.maharjan.kafka.dto.Customer;
import com.maharjan.kafka.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMsgPublisher {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 2, null, message);
        future.whenComplete((result, ex) -> {
            if (ex==null) {
                System.out.println("Sent Message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send Message=[" + message + "] due to " + ex.getMessage());
            }
        });

//        kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 0, null, "Hi");
//        kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 1, null, "Amit");
//        kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 2, null, "Welcome");
//        kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 3, null, "To");
//        kafkaTemplate.send(Constants.KAFKA_TOPIC_PARTITION_5, 4, null, "MyProject");
    }

    public void sendCustomerToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Constants.KAFKA_CUSTOMER_TOPIC, customer);
            future.whenComplete((result, ex) -> {
                if (ex==null) {
                    System.out.println("Sent Message=[" + customer.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send Message=[" + customer.toString() + "] due to " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("Error !! ex.getMessage() = " + ex.getMessage());
            throw ex;
        }
    }
}
