package com.mp1.mas.services;

import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import com.mp1.mas.repositories.StudentRepository;
import com.mp1.mas.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Student> getStudentsOnMaxSemester() {
        return studentRepository.findStudentsByCurrentSemester(Student.getMaksSemester());
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void removeStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            System.out.println("Student with ID " + id + " has been deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }
}
