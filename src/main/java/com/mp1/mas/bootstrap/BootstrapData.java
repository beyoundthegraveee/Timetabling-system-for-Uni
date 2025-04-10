package com.mp1.mas.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;


//Atr. złożony - Klasa User, pole birthDate
//Atr. opcjonalny - Klasa User, pole middleName, Optional<String> getMiddleName(), setMiddleName(Optional<String> middleName)
//Atr. powt. - Klasa Professor, pole List<String> languages
//Atr. klasowy - Klasa Student, pole static int maksSemester
//Atr. pochodny - Klasa Admin, metoda getYearsOfService()
//Met. klasowa - Klasa User, metoda isValidEmail()
//Przesłonięcie - Klasy: User, Student, Professor, Admin, metoda: getUserTypeInfo()
//Przeciążenie - Klasa User, metoda calculateAge()

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserMenu userMenu;

    private final StudentMenu studentMenu;

    private final ProfessorMenu professorMenu;

    private final AdminMenu adminMenu;

    private final Scanner scanner = new Scanner(System.in);


    public BootstrapData(UserMenu userMenu, StudentMenu studentMenu, ProfessorMenu professorMenu, AdminMenu adminMenu) {
        this.userMenu = userMenu;
        this.studentMenu = studentMenu;
        this.professorMenu = professorMenu;
        this.adminMenu = adminMenu;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {

            System.out.println("\nWelcome, you are the Admin of the app.\nСhoose which entity you want to interact with:");
            System.out.println("▶1. User");
            System.out.println("▶2. Student");
            System.out.println("▶3. Professor");
            System.out.println("▶4. Administrator");
            System.out.println("▶0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userMenu.show();
                    break;
                case 2:
                    studentMenu.show();
                    break;
                case 3:
                    professorMenu.show();
                    break;
                case 4:
                    adminMenu.show();
                    break;
                case 0:
                    System.out.println("▶ Exiting the program.");
                    return;
                default:
                    System.out.println("▶ Invalid input, please try again.");
            }
        }

    }


}
