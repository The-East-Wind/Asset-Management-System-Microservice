package com.cg.assetmanagementsystem.employeeservice.dao;

import com.cg.assetmanagementsystem.employeeservice.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee,Integer> {
    Iterable<Employee> findAllByEmployeeNameContainingIgnoreCase(String employeeName);
}
