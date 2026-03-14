package com.example.cours_service;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic studentEnrollmentsTopic() {
        return new NewTopic("student-enrollments", 1, (short) 1);
    }

    @Bean
    public NewTopic courseUpdatesTopic() {
        return new NewTopic("course-updates", 1, (short) 1);
    }
}
