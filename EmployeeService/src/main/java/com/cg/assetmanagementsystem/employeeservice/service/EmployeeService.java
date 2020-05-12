package com.cg.assetmanagementsystem.employeeservice.service;

import com.cg.assetmanagementsystem.employeeservice.entity.Employee;
import com.cg.assetmanagementsystem.employeeservice.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employee> getEmployeesByName(String employeeName);
    List<Employee> getAllEmployees();
}
