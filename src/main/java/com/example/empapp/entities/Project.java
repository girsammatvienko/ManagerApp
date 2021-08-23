package com.example.empapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column
    private String projectName;

    @OneToOne(mappedBy = "project")
    private Manager manager;

    @ManyToMany(mappedBy = "projectsList")
    @JsonIgnore
    private List<Employee> team;
}
