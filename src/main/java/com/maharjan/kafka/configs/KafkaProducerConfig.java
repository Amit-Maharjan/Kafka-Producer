package com.maharjan.kafka.configs;

import com.maharjan.kafka.utils.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic createTopic() {
        return new NewTopic(Constants.KAFKA_TOPIC_PARTITION_5, 5, (short) 1);
    }

    @Bean
    public NewTopic createCustomerTopic() {
        return new NewTopic(Constants.KAFKA_CUSTOMER_TOPIC, 2, (short) 1);
    }
}
