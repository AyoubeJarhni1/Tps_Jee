package com.example.cours_service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentConsumerKafka {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentConsumerKafka.class);

    private final CourseService courseService;

    public EnrollmentConsumerKafka(CourseService courseService) {
        this.courseService = courseService;
    }

    @KafkaListener(
        topics = "student-enrollments",
        groupId = "course-service-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(EnrollmentMessage message) {
        log.info("Reçu inscription Kafka : étudiant {} → cours {}", 
                message.getStudentId(), message.getCourseId());
        courseService.addStudentToCourse(message.getCourseId(), message.getStudentId());
    }
}