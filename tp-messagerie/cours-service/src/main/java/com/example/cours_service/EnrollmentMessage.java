package com.example.cours_service;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long studentId;
   
    private Long courseId;
  

}

