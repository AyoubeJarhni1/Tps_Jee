package com.example.student_service;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.http.ResponseEntity;                 
import org.springframework.web.bind.annotation.PostMapping;     
import org.springframework.web.bind.annotation.RequestBody;      
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentProducerKafka studentProducerKafka;

    public StudentController(StudentProducerKafka studentProducerKafka) {
        this.studentProducerKafka = studentProducerKafka;
    }
    
  @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestBody EnrollmentMessage message) {
        studentProducerKafka.sendEnrollment(message.getStudentId(), message.getCourseId());
        return ResponseEntity.ok("Enrollment message produced to Kafka");
    }

}