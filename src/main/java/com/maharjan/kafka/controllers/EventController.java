package com.maharjan.kafka.controllers;

import com.maharjan.kafka.dto.Customer;
import com.maharjan.kafka.services.KafkaMsgPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private KafkaMsgPublisher kafkaMsgPublisher;

    @GetMapping("/publish/{msg}")
    public ResponseEntity<?> publishMsg(@PathVariable String msg) {
        try {
            for (int i=0; i<10000; i++) {
                kafkaMsgPublisher.sendMessageToTopic(msg + i);
            }
            return ResponseEntity.ok("Message published successfully !!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish/customer")
    public ResponseEntity<Customer> publishCustomer(@RequestBody Customer customer) {
        try {
            kafkaMsgPublisher.sendCustomerToTopic(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception ex) {
            System.out.println("ERROR !! " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
