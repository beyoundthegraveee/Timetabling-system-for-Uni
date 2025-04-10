package com.mp1.mas.services;

import com.mp1.mas.entities.Admin;
import com.mp1.mas.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public Iterable<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void removeAdmin(int id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            System.out.println("Admin with ID " + id + " has been deleted.");
        } else {
            System.out.println("Admin not found.");

        }

    }

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.findById(id).orElse(null);
    }
}
