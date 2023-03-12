package com.example.emsApp.Controller;

import com.example.emsApp.Entity.ProjectManagement;
import com.example.emsApp.Service.EmployeeService;
import com.example.emsApp.Service.ProjectManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectManagementController {
    @Autowired
    private ProjectManagementService projectManagementService;
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Fetch all projects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Projects fetched",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No projects created yet",
                    content = @Content)})
    @GetMapping("/all")
    public ResponseEntity<List<ProjectManagement>> getAllProjects() {
        List<ProjectManagement> projects = projectManagementService.getAllProjects();
        if (projects.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Operation(summary = "Fetch project by ID from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Project fetched",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Entered project ID does not exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProjectManagement>> getProject(@PathVariable Long id) {
        if (!projectManagementService.getProject(id).equals(id)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(projectManagementService.getProject(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Create project and store it to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Project created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "If non-existent employee is put in project: IllegalArgumentException(\"Employee \" + employeeOnProject + \" does not exist\") is thrown",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ProjectManagement> createProject(@RequestBody ProjectManagement projectManagement) {
        projectManagementService.checkEmployeesInProject(projectManagement, employeeService);
        return new ResponseEntity<>(projectManagementService.createProject(projectManagement), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete project from database")
    @ApiResponse(responseCode = "204",
            description = "Project deleted",
            content = {@Content(mediaType = "application/json")})
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectManagement> deleteProject(@PathVariable Long id) {
        projectManagementService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
