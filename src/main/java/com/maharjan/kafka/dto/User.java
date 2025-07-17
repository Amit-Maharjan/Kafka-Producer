package com.maharjan.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String ipAddress;
}
