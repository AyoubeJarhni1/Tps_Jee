package com.example.cours_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EnrollmentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentConsumer.class);

    private final CourseService courseService;

    public EnrollmentConsumer(CourseService courseService) {
        this.courseService = courseService;
    }

    // @RabbitListener(queues = RabbitConfig.QUEUE)
    // public void handleEnrollment(EnrollmentMessage message) {
    //     logger.info("Received enrollment message: {}", message);

    //     courseService.addStudentToCourse(
    //             message.getCourseId(),
    //             message.getStudentId()
    //     );

    //     logger.info("Student {} added to course {}", 
    //         message.getStudentId(),
    //         message.getCourseId()
    //     );
    // }
}