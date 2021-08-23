package com.example.empapp.controllers;

import com.example.empapp.entities.Employee;
import com.example.empapp.entities.exceptions.DepartmentNotExistsException;
import com.example.empapp.entities.exceptions.EmployeeNotExistsException;
import com.example.empapp.entities.exceptions.EmployeeAlreadyExistsException;
import com.example.empapp.entities.exceptions.ManagerNotExistsException;
import com.example.empapp.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private ManagementService managementService;

    @Autowired
    public EmployeeController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee)
            throws EmployeeAlreadyExistsException {
        return managementService.addEmployee(employee);
    }

    @PostMapping("/assign")
    public Boolean assignEmployeeToDepartment(@RequestParam String email, @RequestParam String departmentName)
            throws EmployeeNotExistsException, DepartmentNotExistsException {
        return managementService.assignEmployeeToDepartmentByEmailAddress(email, departmentName);
    }

    @PostMapping("/addToTeam")
    public void addEmployeeToTeam(@RequestParam String email, @RequestParam String projectName) throws ManagerNotExistsException, EmployeeAlreadyExistsException {
        managementService.addEmployeeToTeam(email, projectName);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return managementService.getAllEmployees();
    }

    @PostMapping("/delete")
    public void deleteEmployee(@RequestParam String email) throws EmployeeAlreadyExistsException {
        managementService.deleteEmployee(email);
    }

}
