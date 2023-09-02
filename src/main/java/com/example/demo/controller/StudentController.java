package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @PostMapping("/student")
    public  String addStudent(Student student){
        return "Student Added" + student;

    }

}
