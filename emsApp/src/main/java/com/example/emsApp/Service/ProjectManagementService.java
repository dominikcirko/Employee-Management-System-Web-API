package com.example.emsApp.Service;

import com.example.emsApp.Entity.ProjectManagement;

import java.util.List;
import java.util.Optional;

public interface ProjectManagementService {

    Optional<ProjectManagement> getProject(Long id);
    List<ProjectManagement> getAllProjects();
    void deleteProject(Long id);
    ProjectManagement createProject(ProjectManagement projectManagement);
    void checkEmployeesInProject(ProjectManagement projectManagement, EmployeeService employeeService);

}
