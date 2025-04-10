package com.mp1.mas.services;

import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import org.springframework.stereotype.Service;


public interface StudentService {

    Iterable<Student> getAllStudents();

    Iterable<Student> getStudentsOnMaxSemester();

    void addStudent(Student student);

    void removeStudent(int id);

    Student getStudentById(int id);


}
