package com.mp1.mas.entities;

import com.mp1.mas.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @Column(nullable = true)
    private String middleName; //Atr. opcjonalny

    @Column(nullable = false)
    private LocalDate birthDate; //Atr. złożony

    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected User() {

    }

    public User(String firstName, String lastName, LocalDate birthDate, String emailAddress, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    public User(String firstName, String lastName, String middleName, LocalDate birthDate, String emailAddress, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    //Przesłonięcie
    public String getUserTypeInfo(){
        String middleNameStr = getMiddleName().orElse("None");
        return "User with email: " + emailAddress +" and Full Name: "+ firstName + " " + lastName + " " + middleNameStr;
    };

    //Met. klasowa
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    //Atr. opcjonalny
    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName);
    }

    //Atr. opcjonalny
    public void setMiddleName(Optional<String> middleName) {
        this.middleName = middleName.orElse(null);
    }

    //Przeciążenie
    public int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    //Przeciążenie
    public String calculateAge(boolean inMonths) {
        if (inMonths) {
            long months = ChronoUnit.MONTHS.between(birthDate, LocalDate.now());
            return "Age - " + months + " months";
        } else {
            return "Age - " + calculateAge() + " years";
        }
    }

    //Przeciążenie
    public String calculateAge(boolean inMonths, boolean inDays) {
        long days = ChronoUnit.DAYS.between(birthDate, LocalDate.now());
        long months = ChronoUnit.MONTHS.between(birthDate, LocalDate.now());
        if (inMonths && inDays) {
            return "Age - " + days + " days" + ", months " + months;
        }
        return "Age - " + calculateAge() + " years";
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + (middleName == null? "None": middleName) + '\'' +
                ", birthDate=" + birthDate +
                ", emailAddress='" + emailAddress + '\'' +
                ", role=" + role +
                '}';
    }
}
