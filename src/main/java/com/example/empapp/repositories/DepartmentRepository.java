package com.example.empapp.repositories;

import com.example.empapp.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
    Optional<Department> findByDepartmentName(String departmentName);
    Boolean existsByDepartmentName(String departmentName);
}
