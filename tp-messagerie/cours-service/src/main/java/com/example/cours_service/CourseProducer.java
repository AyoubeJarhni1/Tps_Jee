package com.example.cours_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseProducer {

    @Autowired
    private KafkaTemplate<String, CourseUpdateRequest> kafkaTemplate;

    private static final String TOPIC = "course-updates";

    public void sendUpdate(CourseUpdateRequest request) {
        kafkaTemplate.send(TOPIC, request);
        System.out.println("Mise à jour cours envoyée : " + request);
    }

}
