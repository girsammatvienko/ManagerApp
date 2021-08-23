package com.example.empapp;

import com.example.empapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpAppApplication.class, args);
    }

}
