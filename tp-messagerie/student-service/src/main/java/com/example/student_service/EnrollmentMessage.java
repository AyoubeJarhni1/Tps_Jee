package com.example.student_service;
import java.io.Serializable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long studentId;
    private Long courseId;
   

}


