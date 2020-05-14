package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class DataFetcher {
    private final String employeeServiceURL = "http://employee-service/employees/";
    @Autowired
    private RestTemplate restTemplate;
    public EmployeeDTO fetchEmployeeFromEmployeeService(int employeeId) throws HttpClientErrorException{
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity = restTemplate.getForEntity(employeeServiceURL + employeeId, EmployeeDTO.class);
        return employeeDTOResponseEntity.getBody();
    }
}
