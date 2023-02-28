package com.example.emsApp.Service;

import com.example.emsApp.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee (Employee employee);
    void deleteEmployee(Long id);
    Optional<Employee> getEmployee(Long id);
    List<Employee> getAllEmployees();
    void addEmployeeDepartment(Employee employee, DepartmentService departmentService);
}
