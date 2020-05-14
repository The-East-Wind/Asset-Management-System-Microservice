package com.cg.assetmanagementsystem.requestservice.util;

import com.cg.assetmanagementsystem.requestservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.requestservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.requestservice.exception.DataFetchException;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class DataFetcher {
    @Autowired
    private RestTemplate restTemplate;
    private final String employeeServiceURL = "http://employee-service/employees/";
    private final String assetServiceURL = "http://asset-service/assets/";
    @HystrixCommand(fallbackMethod = "returnDefaultEmployee",ignoreExceptions = {InvalidRequestException.class})
    public EmployeeDTO getFromEmployeeServiceEmployeeWithId(int employeeId) throws InvalidRequestException {
        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity = null;
        try {
            employeeDTOResponseEntity = restTemplate.getForEntity(employeeServiceURL + employeeId, EmployeeDTO.class);
        }
        catch (HttpClientErrorException exception){
            if(exception.getRawStatusCode()==404){
                throw new InvalidRequestException("No employee found with the given ID",exception);
            }
            throw new DataFetchException("Could not fetch data from Employee Service",exception);
        }
        return employeeDTOResponseEntity.getBody();
    }
    public EmployeeDTO returnDefaultEmployee(int employeeId){
        return new EmployeeDTO(employeeId,"","","");
    }
    @HystrixCommand(fallbackMethod = "returnDefaultAsset",ignoreExceptions = {InvalidRequestException.class})
    public AssetDTO getFromAssetServiceAssetWithId(int assetId) throws InvalidRequestException{
        ResponseEntity<AssetDTO> assetDTOResponseEntity = null;
        try{
            assetDTOResponseEntity = restTemplate.getForEntity(assetServiceURL+assetId,AssetDTO.class);
        }
        catch (HttpClientErrorException exception){
            if(exception.getRawStatusCode()==404){
                throw new InvalidRequestException("No employee found with the given ID",exception);
            }
            throw new DataFetchException("Could not fetch data from Asset Service",exception);
        }
        return assetDTOResponseEntity.getBody();
    }
    public AssetDTO returnDefaultAsset(int assetId){
        return new AssetDTO(assetId,"","","","");
    }
}

