package com.example.emsApp.Controller;

import com.example.emsApp.Entity.Department;
import com.example.emsApp.Service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Fetch all departments from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Projects fetched",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No departments created yet",
                    content = @Content)})
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        if (departments.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Operation(summary = "Create department and save it to database")
    @ApiResponse(responseCode = "201",
            description = "Department created",
            content = {@Content(mediaType = "application/json")})
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        departmentService.getDepartments().add(department); //add department to list of departments
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete department from database")
    @ApiResponse(responseCode = "204",
            description = "Department deleted",
            content = {@Content(mediaType = "application/json")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
