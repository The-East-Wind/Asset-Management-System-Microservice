package com.cg.assetmanagementsystem.employeeservice.service;

import com.cg.assetmanagementsystem.employeeservice.dao.EmployeeDAO;
import com.cg.assetmanagementsystem.employeeservice.entity.Employee;
import com.cg.assetmanagementsystem.employeeservice.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDAO employeeDAO;
    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeDAO.findById(employeeId);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        throw new EmployeeNotFoundException("No employee found with id:"+employeeId+".");
    }

    @Override
    public List<Employee> getEmployeesByName(String employeeName) {
        return (List<Employee>) employeeDAO.findAllByEmployeeNameContainingIgnoreCase(employeeName);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDAO.findAll();
    }
}
