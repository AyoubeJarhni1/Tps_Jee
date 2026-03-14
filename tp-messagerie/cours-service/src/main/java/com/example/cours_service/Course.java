package com.example.cours_service;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Course {
    private Long     id;
    private String name;
    private String description;
    private String teacher;
    private List<Long> studentIds = new ArrayList<>();
}
