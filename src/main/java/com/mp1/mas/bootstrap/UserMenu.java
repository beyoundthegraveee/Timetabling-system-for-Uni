package com.mp1.mas.bootstrap;

import com.mp1.mas.entities.User;
import com.mp1.mas.enums.Role;
import com.mp1.mas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class UserMenu {

    private final UserService userService;

    private static final Scanner scanner = new Scanner(System.in);
    private final TransactionAutoConfiguration transactionAutoConfiguration;

    @Autowired
    public UserMenu(UserService userService, TransactionAutoConfiguration transactionAutoConfiguration) {
        this.userService = userService;
        this.transactionAutoConfiguration = transactionAutoConfiguration;
    }


    public void show() {
        System.out.println("\nYou selected: User menu");
        System.out.println("▶1. Show all users");
        System.out.println("▶2. Add new user");
        System.out.println("▶3. Delete user");
        System.out.println("▶4. Calculate user age");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                for (User user : userService.getAllUsers()) {
                    System.out.println(user.toString());
                }
                return;
            case 2:
                addUser();
                return;
            case 3:
                deleteUser();
                return;
            case 4:
                calculateUserAge();
            case 0:
                return;
            default:
                System.out.println("▶Invalid input.");
        }
    }

    public void calculateUserAge(){
        System.out.println("▶Enter user ID to calculate age:");
        int userId = scanner.nextInt();
        User user = userService.getUserById(userId);
        if (user != null) {
            System.out.println("▶ Choose age format:");
            System.out.println("1. Age in years");
            System.out.println("2. Age in months");
            System.out.println("3. Age in days and months");

            int ageChoice = scanner.nextInt();

            switch (ageChoice) {
                case 1:
                    System.out.println("▶Years: " + user.calculateAge());
                    return;
                case 2:
                    System.out.println("▶Months: " +user.calculateAge(true));
                    return;
                case 3:
                    System.out.println("▶Months and days: " +user.calculateAge(true,true));
                    return;
                default:
                    System.out.println("▶ Invalid choice.");
                    break;
            }
        }else {
            System.out.println("▶ User not found.");
        }

    }

    public void deleteUser() {
        System.out.println("▶Enter user ID to delete:");
        int userId = scanner.nextInt();
        userService.removeUserById(userId);
    }

    public void addUser(){
        System.out.println("▶ Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("▶ Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("▶ Enter middle name (or press Enter to skip):");
        String middleName = scanner.nextLine();
        middleName = middleName.isEmpty() ? null : middleName;

        System.out.println("▶ Enter birth date (yyyy-MM-dd):");
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("▶ Invalid date format.");
            return;
        }

        System.out.println("▶ Enter email address:");
        String email = scanner.nextLine();
        if (!User.isValidEmail(email)) {
            System.out.println("▶ Invalid email format. Please enter a valid email.");
            return;
        }

        System.out.println("▶Enter role (student, administrator, professor):");
        String roleInput = scanner.nextLine().toUpperCase();

        if (!isValidRole(roleInput)) {
            System.out.println("▶Invalid role. Please enter one of: student, administrator, professor");
            return ;
        }

        Role userRole = Role.valueOf(roleInput);

        User newUser = new User(firstName, lastName, middleName, birthDate, email, userRole);
        System.out.println(newUser.getUserTypeInfo());
        System.out.println("User added successfully.");
        userService.addUser(newUser);
    }

    public User createUser(String role) {
        System.out.println("▶ Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("▶ Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("▶ Enter middle name (or press Enter to skip):");
        String middleName = scanner.nextLine();
        middleName = middleName.isEmpty() ? null : middleName;

        System.out.println("▶ Enter birth date (yyyy-MM-dd):");
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("▶ Invalid date format.");
            return null;
        }

        System.out.println("▶ Enter email address:");
        String email = scanner.nextLine();
        if (!User.isValidEmail(email)) {
            System.out.println("▶ Invalid email format. Please enter a valid email.");
            return null;
        }
        Role userRole = Role.valueOf(role.toUpperCase());

        User newUser = new User(firstName, lastName, middleName, birthDate, email, userRole);
        return newUser;
    }


    private boolean isValidRole(String roleInput) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleInput)) {
                return true;
            }
        }
        return false;
    }


}
