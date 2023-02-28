package com.example.emsApp.Repository;

import com.example.emsApp.Entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
