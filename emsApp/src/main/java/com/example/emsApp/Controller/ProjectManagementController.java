package com.example.emsApp.Controller;

import com.example.emsApp.Entity.Department;
import com.example.emsApp.Entity.Employee;
import com.example.emsApp.Entity.ProjectManagement;
import com.example.emsApp.Service.EmployeeService;
import com.example.emsApp.Service.ProjectManagementService;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectManagementController {
    @Autowired
    ProjectManagementService projectManagementService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectManagement>> getAllProjects() {
        List<ProjectManagement> projects = projectManagementService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProjectManagement>> getProject(@PathVariable Long id) {
        return new ResponseEntity<>(projectManagementService.getProject(id), HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<ProjectManagement> createProject(@RequestBody ProjectManagement projectManagement) {
        projectManagementService.checkEmployeesInProject(projectManagement, employeeService);
        return new ResponseEntity<>(projectManagementService.createProject(projectManagement), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id) {
        projectManagementService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
