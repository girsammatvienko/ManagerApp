package com.example.empapp.controllers;

import com.example.empapp.entities.Employee;
import com.example.empapp.entities.Project;
import com.example.empapp.entities.exceptions.ManagerNotExistsException;
import com.example.empapp.entities.exceptions.ProjectAlreadyExistsException;
import com.example.empapp.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ManagementService managementService;

    @Autowired
    public ProjectController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/add")
    public Project addProject(@RequestBody Project project) throws ProjectAlreadyExistsException {
        return managementService.addProject(project);
    }

    @GetMapping("/getAll")
    public List<Project> getAllProjects() {
        return managementService.getAllProjects();
    }

    @GetMapping("/getTeam")
    public List<Employee> getTeamOfProject(@RequestParam String projectName) throws ManagerNotExistsException {
        System.out.println("controller");
        return managementService.getTeamOfProject(projectName);
    }



}
