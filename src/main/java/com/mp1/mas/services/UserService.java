package com.mp1.mas.services;

import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import com.mp1.mas.enums.Role;
import com.mp1.mas.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


public interface UserService {

    Iterable<User> getAllUsers();

    void addUser(User user);

    void removeUserById(int id);

    User getUserById(int id);



}
