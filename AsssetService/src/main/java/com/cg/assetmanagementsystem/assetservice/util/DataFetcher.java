package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.exception.ServiceDownException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "returnEmptyEmployee",ignoreExceptions = {HttpClientErrorException.class})
    public EmployeeDTO fetchEmployeeFromEmployeeService(int employeeId) throws HttpClientErrorException{
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity;
        try {
            employeeDTOResponseEntity = restTemplate.getForEntity(employeeServiceURL + employeeId, EmployeeDTO.class);
        }
        catch (HttpClientErrorException httpClientErrorException){
            if(httpClientErrorException.getRawStatusCode()==404){
                throw httpClientErrorException;
            }
            else {
                throw new ServiceDownException("employee-service is down!");
            }
        }
        return employeeDTOResponseEntity.getBody();
    }
    public EmployeeDTO returnEmptyEmployee(int employeeId){
        return new EmployeeDTO(employeeId,"Default Name","Default Department","Default Designation");
    }
}
