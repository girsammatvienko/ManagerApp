package com.example.empapp.repositories;

import com.example.empapp.entities.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
    Boolean existsByName(String name);
    Optional<Manager> findByName(String name);
}
