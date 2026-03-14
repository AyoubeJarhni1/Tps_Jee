package com.example.student_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class StudentProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEnrollment(Long studentId, Long courseId) {
    EnrollmentMessage msg = new EnrollmentMessage(studentId, courseId);
    rabbitTemplate.convertAndSend(
        RabbitConfig.EXCHANGE,
        RabbitConfig.ROUTING_KEY,
        msg
    );
}

}
