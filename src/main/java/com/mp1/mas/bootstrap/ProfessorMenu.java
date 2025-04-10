package com.mp1.mas.bootstrap;

import com.mp1.mas.entities.Professor;
import com.mp1.mas.entities.User;
import com.mp1.mas.enums.Role;
import com.mp1.mas.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ProfessorMenu {

    private final ProfessorService professorService;

    private final UserMenu userMenu;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ProfessorMenu(ProfessorService professorService, UserMenu userMenu) {
        this.professorService = professorService;
        this.userMenu = userMenu;
    }

    public void show() {
        System.out.println("\nYou selected: Professor");
        System.out.println("▶1. Show all professors");
        System.out.println("▶2. Add new professor");
        System.out.println("▶3. Delete professor");
        System.out.println("▶4. Show professors and languages they know");
        System.out.println("▶0. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                for (Professor p : professorService.getAllProfessors()) {
                    System.out.println(p);
                }
                return;
            case 2:
                addProfessor();
                return;
            case 3:
                deleteProfessor();
                return;
            case 4:
                for (Professor p : professorService.getAllProfessors()) {
                    System.out.println(p.getFirstName() +" " + p.getLastName() + ": "+p.getLanguages());
                }
            case 0:
                return;
            default:
                System.out.println("▶Invalid input.");
        }
    }

    public void addProfessor() {

        User userProfessor = userMenu.createUser("PROFESSOR");

        if (userProfessor == null) {
            System.out.println("▶ Failed to create user. Professor not added.");
            return;
        }

        System.out.println("▶Enter employment date (yyyy-MM-dd):");
        LocalDate employmentDate;
        try {
            employmentDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("▶Invalid date format.");
            return;
        }

        System.out.println("▶Enter phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("▶Enter academic degree:");
        String academicDegree = scanner.nextLine();

        System.out.println("▶Enter department name:");
        String departmentName = scanner.nextLine();

        System.out.println("▶Enter languages for the professor:");
        String languagesInput = scanner.nextLine();

        List<String> languages = Arrays.stream(languagesInput.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        Professor professor = new Professor(
                userProfessor.getFirstName(),
                userProfessor.getLastName(),
                userProfessor.getBirthDate(),
                userProfessor.getEmailAddress(),
                userProfessor.getRole(),
                employmentDate,
                phoneNumber,
                academicDegree,
                departmentName,
                languages
        );


        System.out.println(professor.getUserTypeInfo());
        professorService.addProfessor(professor);
        System.out.println("▶Professor added successfully!");
    }

    public void deleteProfessor() {
        System.out.println("▶ Enter professor's ID to delete:");
        int id = scanner.nextInt();

        if (professorService.getProfessorById(id) != null) {
            professorService.removeProfessor(id);
        } else {
            System.out.println("▶ Professor not found.");
        }
    }
}
