package com.mp1.mas.bootstrap;

import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import com.mp1.mas.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentMenu {

    private final StudentService studentService;

    private final UserMenu userMenu;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public StudentMenu(StudentService studentService, UserMenu userMenu) {
        this.studentService = studentService;
        this.userMenu = userMenu;
    }


    public void show() {
        System.out.println("\nYou selected: Student menu");
        System.out.println("▶1. Show all students");
        System.out.println("▶2. Show students in max sem");
        System.out.println("▶3. Add new student");
        System.out.println("▶4. Delete student");
        System.out.println("▶0. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                for (Student s : studentService.getAllStudents()) {
                    System.out.println(s);
                }
                break;
            case 2:
                System.out.println("▶ Students on the maximum semester (" + Student.getMaksSemester() + "):");
                for (Student s : studentService.getStudentsOnMaxSemester()) {
                    System.out.println(s);
                }
                break;
            case 3:
                addStudent();
                break;
            case 4:
                deleteStudent();
            case 0:
                return;
            default:
                System.out.println("▶Invalid input.");
        }
    }

    public void addStudent() {
        System.out.println("▶ Creating a new user...");

        User newUser = userMenu.createUser("STUDENT");

        if (newUser == null) {
            System.out.println("▶ Failed to create user. Student not added.");
            return;
        }

        System.out.println("▶ Enter nationality:");
        String nationality = scanner.nextLine();

        System.out.println("▶ Enter student number:");
        String studentNumber = scanner.nextLine();

        System.out.println("▶ Enter current semester (1-" + Student.getMaksSemester() + "):");
        int currentSemester;
        try {
            currentSemester = Integer.parseInt(scanner.nextLine());
            if (currentSemester < 1 || currentSemester > Student.getMaksSemester()) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.out.println("▶ Invalid semester. Must be between 1 and " + Student.getMaksSemester());
            return;
        }

        Student student = new Student(
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getBirthDate(),
                newUser.getEmailAddress(),
                newUser.getRole(),
                studentNumber,
                nationality,
                currentSemester);

        if (newUser.getMiddleName() != null && !newUser.getMiddleName().isEmpty()) {
            student.setMiddleName(newUser.getMiddleName());
        }

        studentService.addStudent(student);
        System.out.println("▶ Student added successfully!");
    }

    public void deleteStudent() {
        System.out.println("▶ Enter student ID to delete:");
        int id = scanner.nextInt();

        if (studentService.getStudentById(id) != null) {
            studentService.removeStudent(id);
        } else {
            System.out.println("▶ Student not found.");
        }
    }
}
