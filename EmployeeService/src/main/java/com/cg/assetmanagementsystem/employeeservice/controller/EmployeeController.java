package com.cg.assetmanagementsystem.employeeservice.controller;

import com.cg.assetmanagementsystem.employeeservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.employeeservice.entity.Employee;
import com.cg.assetmanagementsystem.employeeservice.exception.EmployeeNotFoundException;
import com.cg.assetmanagementsystem.employeeservice.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employees",produces = "application/json")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Asset Management System Employee Service", produces = "application/json")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "GET Employee with a given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Employee with give ID."),
            @ApiResponse(code = 404, message = "Employee Not Found.")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeWithId(
            @ApiParam(value = "ID of the Employee you want to retrieve.", required = true)
            @PathVariable(name = "id") int employeeId
    ) throws EmployeeNotFoundException{
        Employee employeeWithId = employeeService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employeeWithId.getEmployeeId(),
                employeeWithId.getEmployeeName(),
                employeeWithId.getEmployeeDepartment(),
                employeeWithId.getEmployeeDesignation()
        );
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
    @ApiOperation(value = "GET All Employees in the database.",response = List.class)
    @GetMapping
    public List<EmployeeDTO> getEmployees(
            @ApiParam(value = "Name of the Employee(s) you wish to retrieve.")
            @RequestParam(name = "name",required = false) String employeeName,
            @ApiParam(value = "ID of the Employee you wish to retrieve.")
            @RequestParam(name = "id",required = false) Integer employeeId
    ) throws EmployeeNotFoundException{
        if(employeeId!=null){
            Employee employeeWithId = employeeService.getEmployeeById(employeeId);
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            employeeDTOS.add(new EmployeeDTO(
                    employeeWithId.getEmployeeId(),
                    employeeWithId.getEmployeeName(),
                    employeeWithId.getEmployeeDepartment(),
                    employeeWithId.getEmployeeDesignation()
            ));
            return employeeDTOS;
        }
        List<Employee> employees;
        if(employeeName!=null){
            employees = employeeService.getEmployeesByName(employeeName);
        }
        else {
            employees = employeeService.getAllEmployees();
        }
        List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeDepartment(),
                employee.getEmployeeDesignation()
        )).collect(Collectors.toList());
        return employeeDTOs;
    }
}
