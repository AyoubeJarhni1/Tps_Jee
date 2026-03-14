package com.example.student_service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentProducerKafka {

   private final KafkaTemplate<String, EnrollmentMessage> kafkaTemplate;

   

    public void sendEnrollment(Long studentId, Long courseId) {
        EnrollmentMessage message = new EnrollmentMessage(studentId, courseId);
        kafkaTemplate.send("student-enrollments", message);
        System.out.println("Envoyé : " + message);
    }
}
