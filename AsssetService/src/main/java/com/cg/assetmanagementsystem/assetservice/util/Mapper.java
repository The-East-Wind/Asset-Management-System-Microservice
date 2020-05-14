package com.cg.assetmanagementsystem.assetservice.util;

import com.cg.assetmanagementsystem.assetservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class Mapper {
    @Autowired
    private DataFetcher dataFetcher;
    public AssetDTO entityToDTO(Asset asset)  {
        AssetDTO assetDTO = new AssetDTO(
                asset.getAssetId(),
                asset.getAssetName(),
                asset.getAssetDescription(),
                asset.getAssetCategory(),
                asset.getAvailability()
        );
        if(asset.getAllottedTo()!=null){
            try {
                assetDTO.setAllottedTo(dataFetcher.fetchEmployeeFromEmployeeService(asset.getAllottedTo()));
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

    public Asset dtoToEntity(AssetDTO assetDTO){
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
