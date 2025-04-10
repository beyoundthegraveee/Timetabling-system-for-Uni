package com.mp1.mas.entities;

import com.mp1.mas.enums.AccountStatus;
import com.mp1.mas.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "administrators")
public class Admin extends User{

    @Column(nullable = false)
    private LocalDate employmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @Column(nullable = false)
    private String accessRights;

    public Admin() {
        super();
    }

    //Przesłonięcie
    @Override
    public String getUserTypeInfo(){
        return "Admin " + this.getFirstName() + " "+ this.getLastName()+" has: " + accessRights + " his account is: " + accountStatus;
    };

    public Admin(String firstName, String lastName, LocalDate birthDate, String emailAddress, Role role, LocalDate employmentDate, AccountStatus accountStatus, String accessRights) {
        super(firstName, lastName, birthDate, emailAddress, role);
        this.employmentDate = employmentDate;
        this.accountStatus = accountStatus;
        this.accessRights = accessRights;
    }

    //Atr. pochodny
    public String getYearsOfService() {
        int years = employmentDate != null ? Period.between(employmentDate, LocalDate.now()).getYears() : 0;
        return "Admin: " + this.getFirstName() + " " + this.getLastName() + " worked for " + years + " years";
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }
}
