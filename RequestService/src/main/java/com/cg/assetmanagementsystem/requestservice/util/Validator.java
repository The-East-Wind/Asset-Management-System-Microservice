package com.cg.assetmanagementsystem.requestservice.util;

import com.cg.assetmanagementsystem.requestservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.requestservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.requestservice.dto.RequestDTO;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Validator {
    private static String employeeServiceURL = "http://employee-service/employees/";
    private static String assetServiceURL = "http://asset-service/assets/";
    public static boolean validateRequest(RequestDTO requestDTO,RestTemplate restTemplate) throws InvalidRequestException{
        if(requestDTO.getRequestedFrom()==null
                ||requestDTO.getRequestedTill()==null
                ||requestDTO.getRequestedBy()==null
                ||requestDTO.getRequestedFor()==null
                ||requestDTO.getRequestedAsset()==null
                ||requestDTO.getStatus()==null
                ||requestDTO.getStatus().trim().isEmpty()
        ){
            throw new InvalidRequestException("Error!Invalid Request.Contains one or more null fields");
        }
        ResponseEntity<EmployeeDTO> responseEntityEmployee;
        ResponseEntity<AssetDTO> responseEntityAsset;
        try {
            responseEntityEmployee = restTemplate.getForEntity(employeeServiceURL + requestDTO.getRequestedFor().getEmployeeId(), EmployeeDTO.class);
            responseEntityEmployee = restTemplate.getForEntity(employeeServiceURL+requestDTO.getRequestedBy().getEmployeeId(),EmployeeDTO.class);
            responseEntityAsset = restTemplate.getForEntity(assetServiceURL+requestDTO.getRequestedAsset().getAssetId(), AssetDTO.class);
        }
        catch (HttpClientErrorException exception){
            throw new InvalidRequestException("Error!Invalid fields in request.",exception);
        }
        return true;
    }
}
