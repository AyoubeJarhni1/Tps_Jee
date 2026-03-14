package com.example.cours_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CoursMessage {
    private Long courseId;
    private String courseName;

}
