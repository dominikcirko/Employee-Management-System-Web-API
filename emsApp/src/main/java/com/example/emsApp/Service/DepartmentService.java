package com.example.emsApp.Service;

import com.example.emsApp.Entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    List<Department> getDepartments();

    void deleteDepartment(Long id);

}
