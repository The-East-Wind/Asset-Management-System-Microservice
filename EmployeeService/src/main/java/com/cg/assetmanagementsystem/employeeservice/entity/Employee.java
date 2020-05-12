package com.cg.assetmanagementsystem.employeeservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int employeeId;
    @Column(name = "emp_name")
    private String employeeName;
    @Column(name = "emp_dept")
    private String employeeDepartment;
    @Column(name = "emp_desgn")
    private String employeeDesignation;

    public Employee() {
    }

    public Employee(String employeeName, String employeeDepartment, String employeeDesignation) {
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
        this.employeeDesignation = employeeDesignation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId());
    }
}
