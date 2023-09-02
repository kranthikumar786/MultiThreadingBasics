package com.example.demo.service;

import com.example.demo.repository.StudentRepository;
import com.example.demo.entity.Student;

public class StudentService {

private StudentRepository studentRepository;

  public StudentService (StudentRepository studentRepository){
      this.studentRepository = studentRepository;
  }
  public void addStudent(Student student){
      studentRepository.save(student);
  }

}
