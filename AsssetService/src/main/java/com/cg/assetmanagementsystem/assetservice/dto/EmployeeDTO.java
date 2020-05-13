package com.cg.assetmanagementsystem.assetservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details of the Employee")
public class EmployeeDTO {
    @ApiModelProperty(notes = "Database Generated ID of the Employee")
    private int employeeId;
    @ApiModelProperty(notes = "Name of the Employee")
    private String employeeName;
    @ApiModelProperty(notes = "Department of the Employee")
    private String employeeDepartment;
    @ApiModelProperty(notes = "Designation of the Employee")
    private String employeeDesignation;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeId, String employeeName, String employeeDepartment, String employeeDesignation) {
        this.employeeId = employeeId;
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
}
