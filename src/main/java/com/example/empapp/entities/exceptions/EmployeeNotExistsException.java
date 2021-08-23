package com.example.empapp.entities.exceptions;

public class EmployeeNotExistsException extends Exception {

    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
