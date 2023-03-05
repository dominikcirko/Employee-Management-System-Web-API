package com.example.emsApp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "department_name", nullable = false)
    private String departmentName;

}
