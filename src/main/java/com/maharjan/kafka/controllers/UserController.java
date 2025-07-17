package com.maharjan.kafka.controllers;

import com.maharjan.kafka.dto.User;
import com.maharjan.kafka.services.UserPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserPublisher userPublisher;

    @PostMapping()
    public ResponseEntity<User> publishUser(@RequestBody User user) {
        try {
            userPublisher.publishUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            System.out.println("ERROR !! " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
