package com.example.emsApp.Controller;

import com.example.emsApp.Entity.Employee;
import com.example.emsApp.Service.DepartmentService;
import com.example.emsApp.Service.EmployeeService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Fetch employee from database using ID number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Employee has been found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Entered employee ID does not exist",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Create an employee and save him to database")
    @ApiResponse(responseCode = "201",
            description = "Employee has been created",
            content = {@Content(mediaType = "application/json")})
    @PostMapping
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        employeeService.addEmployeeDepartment(employee, departmentService);
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete employee from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Employee has been deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Entered employee ID does not exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Fetch all existing employees from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all employees from database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No employees created yet",
                    content = @Content)})
    @GetMapping("/all")  //filter employees by query params
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String email,
                                                          @RequestParam(required = false) String jobTitle,
                                                          @RequestParam(required = false) BigDecimal salary,
                                                          @RequestParam(required = false) Integer age) {

        List<Employee> employees = employeeService.getAllEmployees();
        {
            employees = employees.stream()
                    .filter(employee -> name == null || employee.getName().equals(name))
                    .filter(employee -> email == null || employee.getEmail().equals(email))
                    .filter(employee -> jobTitle == null || employee.getJobTitle().equals(jobTitle))
                    .filter(employee -> salary == null || employee.getSalary().equals(salary))
                    .filter(employee -> age == null || employee.getAge().equals(age))
                    .collect(Collectors.toList());
        }

        if (employees.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}

