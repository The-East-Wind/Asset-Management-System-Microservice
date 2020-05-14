package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class Validator {
    @Autowired
    private DataFetcher dataFetcher;
    public boolean validateInputData(AssetDTO assetDTO) throws InvalidRequestException {
        boolean valid=true;
        if(assetDTO.getAssetName()==null||assetDTO.getAssetName().trim().isEmpty()){
            throw new InvalidRequestException("Asset Name Cannot be Empty.");
        }
        if(assetDTO.getAssetCategory()==null||assetDTO.getAssetCategory().trim().isEmpty()){
            throw new InvalidRequestException("Asset Category Cannot be Empty");
        }
        ResponseEntity<EmployeeDTO> responseEntity;
        try{
            dataFetcher.fetchEmployeeFromEmployeeService(assetDTO.getAllottedTo().getEmployeeId());
        }
        catch (HttpClientErrorException exception){
            if(exception.getRawStatusCode()==404){
                throw new InvalidRequestException("No employee found with the id specified.");
            }
        }
        return true;
    }
}
