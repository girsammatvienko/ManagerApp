package com.example.empapp.services;

import com.example.empapp.entities.Department;
import com.example.empapp.entities.Employee;
import com.example.empapp.entities.Manager;
import com.example.empapp.entities.Project;
import com.example.empapp.entities.exceptions.*;
import com.example.empapp.repositories.DepartmentRepository;
import com.example.empapp.repositories.EmployeeRepository;
import com.example.empapp.repositories.ManagerRepository;
import com.example.empapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private ManagerRepository managerRepository;

    @Autowired
    public ManagementService(EmployeeRepository employeeRepository,
                             DepartmentRepository departmentRepository,
                             ProjectRepository projectRepository,
                             ManagerRepository managerRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.managerRepository = managerRepository;
    }

    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        if(employeeRepository.existsByEmail(employee.getEmail()))
            throw new EmployeeAlreadyExistsException("Employee with that email already exists!");
        return employeeRepository.save(employee);
    }

    public Department addDepartment(Department department) throws DepartmentAlreadyExistsException {
        if(departmentRepository.existsByDepartmentName(department.getDepartmentName()))
            throw new DepartmentAlreadyExistsException("Department with that name already exists!");
        return departmentRepository.save(department);
    }

    public Project addProject(Project project) throws ProjectAlreadyExistsException {
        if(projectRepository.existsByProjectName(project.getProjectName()))
            throw new ProjectAlreadyExistsException("Project with that name already exists!");
        return projectRepository.save(project);
    }

    public Manager addManager(Manager manager) throws ManagerAlreadyExistsException {
        if(managerRepository.existsByName(manager.getName()))
            throw new ManagerAlreadyExistsException("Manager with that name already exists");
        return managerRepository.save(manager);
    }

    public void deleteEmployee(String email) throws EmployeeAlreadyExistsException {
        if(!employeeRepository.existsByEmail(email))
            throw new EmployeeAlreadyExistsException("Employee with that email not exists");
        employeeRepository.delete(employeeRepository.findByEmail(email).get());
    }

    public void addEmployeeToTeam(String email, String projectName) throws ManagerNotExistsException, EmployeeAlreadyExistsException {
        if(!employeeRepository.existsByEmail(email))
            throw new EmployeeAlreadyExistsException("Employee with that email not exists");
        if(!projectRepository.existsByProjectName(projectName))
            throw new ManagerNotExistsException("Project with that name not exists");
        Employee employee = employeeRepository.findByEmail(email).get();
        Project project = projectRepository.findByProjectName(projectName).get();

        project.getTeam().add(employee);
        employee.getProjectsList().add(project);

        employeeRepository.save(employee);
        projectRepository.save(project);
    }

    public List<Employee> getTeamOfProject(String projectName) throws ManagerNotExistsException {
        if(!projectRepository.existsByProjectName(projectName))
            throw new ManagerNotExistsException("Project with that name not exists");
        return projectRepository.findByProjectName(projectName).get().getTeam();
    }

    public Boolean setProjectManager(String managerName, String projectName) throws EmployeeNotExistsException, ProjectNotExistsException, ManagerNotExistsException {
        if(!managerRepository.existsByName(managerName))
            throw new ManagerNotExistsException("Manager with that name not exists");
        if(!projectRepository.existsByProjectName(projectName))
            throw new ManagerNotExistsException("Project with that name not exists");
        Manager manager = managerRepository.findByName(managerName).get();
        Project project = projectRepository.findByProjectName(projectName).get();

        manager.setProject(project);
        project.setManager(manager);

        managerRepository.save(manager);
        projectRepository.save(project);
        return true;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Employee> getAllEmployeesByDepartment(String departmentName) throws DepartmentNotExistsException {
        if(!departmentRepository.existsByDepartmentName(departmentName))
            throw new DepartmentNotExistsException("Department with that name not exists");
        return employeeRepository.findAllByDepartmentDepartmentName(departmentName);
    }

    public Boolean assignEmployeeToDepartmentByEmailAddress(String email, String departmentName) throws EmployeeNotExistsException, DepartmentNotExistsException {
        if(!employeeRepository.existsByEmail(email)) {
            throw new EmployeeNotExistsException("Employee with that email not exists");
        }
        if(!departmentRepository.existsByDepartmentName(departmentName))
            throw new DepartmentNotExistsException("Department with that name not exists");

        Employee employee = employeeRepository.findByEmail(email).get();
        Department department = departmentRepository.findByDepartmentName(departmentName).get();

        employee.setDepartment(department);
        department.getEmployeeList().add(employee);

        employeeRepository.save(employee);
        departmentRepository.save(department);
        return true;
    }

}
