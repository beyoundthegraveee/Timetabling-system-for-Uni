package com.mp1.mas.services;

import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import com.mp1.mas.enums.Role;
import com.mp1.mas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            System.out.println("User with ID " + id + " has been deleted.");
        } else {
            System.out.println("User not found.");
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }


}
