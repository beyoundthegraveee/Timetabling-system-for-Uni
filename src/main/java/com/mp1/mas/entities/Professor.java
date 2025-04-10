package com.mp1.mas.entities;


import com.mp1.mas.enums.Role;
import com.mp1.mas.utils.StringListConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor extends User {

    @Column(nullable = false)
    private LocalDate employmentDate;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String academicDegree;

    @Column(nullable = false)
    private String departmentName;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<String> languages; //Atr. powt.

    public Professor() {
        super();
    }

    public Professor(String firstName, String lastName, LocalDate birthDate, String emailAddress, Role role, LocalDate employmentDate, String phoneNumber, String academicDegree, String departmentName, List<String> languages) {
        super(firstName, lastName, birthDate, emailAddress, role);
        this.employmentDate = employmentDate;
        this.phoneNumber = phoneNumber;
        this.academicDegree = academicDegree;
        this.departmentName = departmentName;
        this.languages = languages;
    }

    //Przesłonięcie
    @Override
    public String getUserTypeInfo(){
        return "Professor " + this.getFirstName() + " "+ this.getLastName()+" work in: " + departmentName + " is " + academicDegree + " and know: " + languages;
    };

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
