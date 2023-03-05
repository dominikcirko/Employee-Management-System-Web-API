package com.example.emsApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_management")
public class ProjectManagement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "client", nullable = false)
    private String client;
    @Column(name = "project_manager", nullable = false)
    private String projectManager;
    @Column(name = "project_description", nullable = false)
    private String projectDescription;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "max_hours", nullable = false)
    private final Double maxHoursPerEmployee = 40.0;
    @Column(name = "employees_on_project", nullable = false)
    private List<String> employeesOnProject;


}
