package com.mp1.mas.repositories;


import com.mp1.mas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mp1.mas.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
