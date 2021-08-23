package com.example.empapp.repositories;

import com.example.empapp.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();
    Optional<Project> findByProjectName(String projectName);
    Boolean existsByProjectName(String projectName);
}
