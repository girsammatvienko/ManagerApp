package com.example.empapp.controllers;

import com.example.empapp.entities.Manager;
import com.example.empapp.entities.exceptions.EmployeeNotExistsException;
import com.example.empapp.entities.exceptions.ManagerAlreadyExistsException;
import com.example.empapp.entities.exceptions.ManagerNotExistsException;
import com.example.empapp.entities.exceptions.ProjectNotExistsException;
import com.example.empapp.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private ManagementService managementService;

    @Autowired
    public ManagerController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/add")
    public Manager addManager(@RequestBody Manager manager) throws ManagerAlreadyExistsException {
        return managementService.addManager(manager);
    }

    @PostMapping("/assign")
    public Boolean assignManagerToProject(@RequestParam String managerName, @RequestParam String projectName)
            throws ManagerNotExistsException, EmployeeNotExistsException, ProjectNotExistsException {
        return managementService.setProjectManager(managerName, projectName);
    }
}
