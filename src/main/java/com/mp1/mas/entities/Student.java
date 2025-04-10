package com.mp1.mas.entities;


import com.mp1.mas.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student extends User {

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private static int maksSemester = 7; //Atr. klasowy

    @Column(nullable = false)
    private int currentSemester;

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, LocalDate birthDate, String emailAddress, Role role, String studentNumber, String nationality, int currentSemester) {
        super(firstName, lastName, birthDate, emailAddress, role);
        this.studentNumber = studentNumber;
        this.nationality = nationality;
        this.currentSemester = currentSemester;
    }

    public Student(String studentNumber, String nationality, int currentSemester) {
        this.studentNumber = studentNumber;
        this.nationality = nationality;
        this.currentSemester = currentSemester;
    }

    //Przesłonięcie
    @Override
    public String getUserTypeInfo(){
        return "Student " + this.getFirstName() + " " + this.getLastName() + " with student number: " + studentNumber + " is " + nationality;
    };

    public static int getMaksSemester() {
        return maksSemester;
    }

    public static void setMaksSemester(int maksSemester) {
        Student.maksSemester = maksSemester;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        if (currentSemester < 1 || currentSemester > maksSemester) {
            throw new IllegalArgumentException("Semester must be between 1 and " + maksSemester);
        }
        this.currentSemester = currentSemester;
    }
}
