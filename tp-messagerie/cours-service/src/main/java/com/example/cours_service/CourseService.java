package com.example.cours_service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final List<Course> courses = new ArrayList<>(List.of(
        new Course(1L, "Java", "Backend development", "Mr Karim", new ArrayList<>()),
        new Course(2L, "DevOps", "CI/CD pipelines", "Mme Nisrine", new ArrayList<>())
    ));

    public List<Course> getAllCourses() {
        return courses;
    }

    public void addStudentToCourse(Long courseId, Long studentId) {
        courses.stream()
               .filter(c -> c.getId().equals(courseId))
               .findFirst()
               .ifPresent(c -> c.getStudentIds().add(studentId));
    }
}
