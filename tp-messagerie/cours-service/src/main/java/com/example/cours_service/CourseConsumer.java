// package com.example.cours_service;

// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;


// @Service
// public class CourseConsumer {


//     @Autowired
//     private CourseService courseService;

//      @RabbitListener(queues = RabbitConfig.QUEUE)
//     public void handleEnrollment(EnrollmentMessage msg) {
//         System.out.println("Message received: " + msg);

//         courseService.addStudentToCourse(msg.getCourseId(), msg.getStudentId());

//         System.out.println("Student added to course successfully");
//     }
// }
