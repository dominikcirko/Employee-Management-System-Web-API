package com.example.emsApp.Service;

import com.example.emsApp.Entity.Department;
import com.example.emsApp.Entity.Employee;
import com.example.emsApp.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    //check if the entered department name is the same as one inside a list of departments
    //if it is; assign that value to employee.departmentName
    //if it isn't; throw exception
    public void addEmployeeDepartment(Employee employee, DepartmentService departmentService) {
        String employeeDepartmentName = employee.getDepartmentName();
        boolean exists = false;
        if (employeeDepartmentName != null) {
            for (Department department : departmentService.getDepartments()) {
                if (employeeDepartmentName.equals(department.getDepartmentName())) {
                    exists = true;
                    employeeDepartmentName = department.getDepartmentName();
                    break;
                }
            }
            if (exists == false) {
                throw new IllegalArgumentException("Invalid department name: " + employeeDepartmentName);
            }
        }


    }
}

