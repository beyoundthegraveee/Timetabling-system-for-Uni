package com.mp1.mas;

import com.mp1.mas.entities.Professor;
import com.mp1.mas.entities.Student;
import com.mp1.mas.entities.User;
import com.mp1.mas.enums.Role;
import com.mp1.mas.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Mp1Application {


	public static void main(String[] args) {
		SpringApplication.run(Mp1Application.class, args);
	}

}
