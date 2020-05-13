package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Mapper {
    private static String employeeServiceURL = "http://employee-service/employees/";
    public static AssetDTO entityToDTO(Asset asset, RestTemplate restTemplate)  {
        AssetDTO assetDTO = new AssetDTO(
                asset.getAssetId(),
                asset.getAssetName(),
                asset.getAssetDescription(),
                asset.getAssetCategory(),
                asset.getAvailability()
        );
        if(asset.getAllottedTo()!=null){
            try {
                ResponseEntity<EmployeeDTO> responseEntity = restTemplate.getForEntity(employeeServiceURL + asset.getAllottedTo(), EmployeeDTO.class);
                assetDTO.setAllottedTo(responseEntity.getBody());
            }
            catch (HttpClientErrorException exception){
                assetDTO.setAllottedTo(null);
            }
        }
        else {
            assetDTO.setAllottedTo(null);
        }
        return assetDTO;
    }

    public static Asset dtoToEntity(AssetDTO assetDTO){
        Asset asset = new Asset(
          assetDTO.getAssetName(),
          assetDTO.getAssetDescription(),
          assetDTO.getAssetCategory(),
          assetDTO.getAvailability(),
          assetDTO.getAllottedTo()==null?null:assetDTO.getAllottedTo().getEmployeeId()
        );
        asset.setAssetId(assetDTO.getAssetId());
        return asset;
    }
}
