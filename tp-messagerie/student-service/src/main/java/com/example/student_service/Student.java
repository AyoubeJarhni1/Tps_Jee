package com.example.student_service;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Long courseId;
}

