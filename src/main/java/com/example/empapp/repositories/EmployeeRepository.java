package com.example.empapp.repositories;

import com.example.empapp.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
    List<Employee> findAllByEmail(String email);
    List<Employee> findAllByDepartmentDepartmentName(String departmentName);
    Boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
}
