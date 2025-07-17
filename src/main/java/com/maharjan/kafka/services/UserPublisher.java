package com.maharjan.kafka.services;

import com.maharjan.kafka.dto.User;
import com.maharjan.kafka.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserPublisher {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUser(User user) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Constants.KAFKA_USER_TOPIC, user);
            future.whenComplete((result, ex) -> {
                if (ex==null) {
                    System.out.println("Sent Message=[" + user.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send Message=[" + user.toString() + "] due to " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("Error !! ex.getMessage() = " + ex.getMessage());
            throw ex;
        }
    }
}
