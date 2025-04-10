package com.mp1.mas.services;

import com.mp1.mas.entities.Admin;

public interface AdminService {

    Iterable<Admin> getAdmins();

    void addAdmin(Admin admin);

    void removeAdmin(int id);

    Admin getAdminById(int id);




}
