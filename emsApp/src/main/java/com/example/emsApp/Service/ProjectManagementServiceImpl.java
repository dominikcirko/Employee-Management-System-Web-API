package com.example.emsApp.Service;

import com.example.emsApp.Entity.Employee;
import com.example.emsApp.Entity.ProjectManagement;
import com.example.emsApp.Repository.ProjectManagementRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProjectManagementServiceImpl implements ProjectManagementService {
    @Autowired
    private ProjectManagementRepository projectManagementRepository;

    @Override
    public Optional<ProjectManagement> getProject(Long id) {
        return projectManagementRepository.findById(id);
    }

    @Override
    public List<ProjectManagement> getAllProjects() {
        return (List<ProjectManagement>) projectManagementRepository.findAll();
    }

    @Override
    public ProjectManagement createProject(ProjectManagement projectManagement) {
        return projectManagementRepository.save(projectManagement);
    }

    @Override
    public void deleteProject(Long id) {
        projectManagementRepository.deleteById(id);
    }

    @Override
    public void checkEmployeesInProject(ProjectManagement projectManagement, EmployeeService employeeService) {
        List<String> employeesOnProject = projectManagement.getEmployeesOnProject();

        //extract names from employee objects in list of employees
        List<String> validNames = employeeService.getAllEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        //if entered employee exists in employee database, do nothing, else: throw exception
        for (String employeeOnProject : employeesOnProject) {
            if (!validNames.contains(employeeOnProject)) {
                throw new IllegalArgumentException("Employee " + employeeOnProject + " does not exist");
            }
        }
    }

}
