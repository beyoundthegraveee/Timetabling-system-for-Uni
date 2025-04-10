package com.mp1.mas.bootstrap;

import com.mp1.mas.entities.Admin;
import com.mp1.mas.entities.User;
import com.mp1.mas.enums.AccountStatus;
import com.mp1.mas.enums.Role;
import com.mp1.mas.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Component
public class AdminMenu {

    private final AdminService adminService;

    private final UserMenu userMenu;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public AdminMenu(AdminService adminService, UserMenu userMenu) {
        this.adminService = adminService;
        this.userMenu = userMenu;
    }


    public void show() {
        System.out.println("\nYou selected: Administrator");
        System.out.println("▶1. Show all administrators");
        System.out.println("▶2. Add new administrator");
        System.out.println("▶3. Delete administrator");
        System.out.println("▶4. Get years of service for administrators");
        System.out.println("▶0. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                for (Admin admin : adminService.getAdmins()) {
                    System.out.println(admin);
                }
                return;
            case 2:
                addAdmin();
                return;
            case 3:
                deleteAdmin();
            case 4:
                for (Admin admin : adminService.getAdmins()) {
                    System.out.println(admin.getYearsOfService());
                }
            case 0:
                return;
            default:
                System.out.println("▶Invalid input.");
        }
    }

    public void addAdmin() {

        User userAdmin = userMenu.createUser("ADMINISTRATOR");

        if (userAdmin == null) {
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

        System.out.println("▶Select account status (ACTIVE, BLOCKED):");
        String accountStatusInput = scanner.nextLine().toUpperCase();
        AccountStatus accountStatus;
        try {
            accountStatus = AccountStatus.valueOf(accountStatusInput);
        } catch (IllegalArgumentException e) {
            System.out.println("▶Invalid account status. Please enter one of: ACTIVE, INACTIVE, SUSPENDED.");
            return;
        }

        System.out.println("▶Enter access rights description:");
        String accessRights = scanner.nextLine();

        Admin admin = new Admin(
                userAdmin.getFirstName(),
                userAdmin.getLastName(),
                userAdmin.getBirthDate(),
                userAdmin.getEmailAddress(),
                userAdmin.getRole(),
                employmentDate,
                accountStatus,
                accessRights
        );
        System.out.println(admin.getUserTypeInfo());

        adminService.addAdmin(admin);
        System.out.println("▶Administrator added successfully: " + admin);
    }


    public void deleteAdmin() {
        System.out.println("▶ Enter admin's ID to delete:");
        int id = scanner.nextInt();

        if (adminService.getAdminById(id) != null) {
            adminService.removeAdmin(id);
        } else {
            System.out.println("▶ Admin not found.");
        }
    }
}
