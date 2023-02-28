package com.example.emsApp.Repository;

import com.example.emsApp.Entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findByDepartmentId(Long departmentId);
}
