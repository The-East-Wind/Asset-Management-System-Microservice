package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Validator {
    private static String employeeServiceURL="http://employee-service/employees/";
    public static boolean validateInputData(AssetDTO assetDTO, RestTemplate restTemplate) throws InvalidRequestException {
        boolean valid=true;
        if(assetDTO.getAssetName()==null||assetDTO.getAssetName().trim().isEmpty()){
            throw new InvalidRequestException("Asset Name Cannot be Empty.");
        }
        if(assetDTO.getAssetCategory()==null||assetDTO.getAssetCategory().trim().isEmpty()){
            throw new InvalidRequestException("Asset Category Cannot be Empty");
        }
        ResponseEntity<EmployeeDTO> responseEntity;
        try{
            responseEntity = restTemplate.getForEntity(employeeServiceURL+assetDTO.getAllottedTo().getEmployeeId(),EmployeeDTO.class);
        }
        catch (HttpClientErrorException exception){
            if(exception.getRawStatusCode()==404){
                throw new InvalidRequestException("No employee found with the id specified.");
            }
        }
        return true;
    }
}
