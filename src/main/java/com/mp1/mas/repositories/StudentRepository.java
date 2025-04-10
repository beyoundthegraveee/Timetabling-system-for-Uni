package com.mp1.mas.repositories;


import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Iterable<Student> findStudentsByCurrentSemester(int currentSemester);



}
