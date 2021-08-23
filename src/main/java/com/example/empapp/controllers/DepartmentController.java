package com.example.empapp.controllers;

import com.example.empapp.entities.Department;
import com.example.empapp.entities.Employee;
import com.example.empapp.entities.exceptions.DepartmentAlreadyExistsException;
import com.example.empapp.entities.exceptions.DepartmentNotExistsException;
import com.example.empapp.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private ManagementService managementService;

    @Autowired
    public DepartmentController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department)
            throws DepartmentAlreadyExistsException {
       return managementService.addDepartment(department);
    }

    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return managementService.getAllDepartments();
    }

    @GetMapping("/getStaff")
    public List<Employee> getEmployeesByDepartment(@RequestParam String departmentName)
            throws DepartmentNotExistsException {
        return managementService.getAllEmployeesByDepartment(departmentName);
    }

}
