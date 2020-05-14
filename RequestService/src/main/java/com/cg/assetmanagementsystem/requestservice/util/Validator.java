package com.cg.assetmanagementsystem.requestservice.util;

import com.cg.assetmanagementsystem.requestservice.dto.RequestDTO;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    @Autowired
    private DataFetcher dataFetcher;
    public boolean validateRequest(RequestDTO requestDTO) throws InvalidRequestException{
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
        dataFetcher.getFromEmployeeServiceEmployeeWithId(requestDTO.getRequestedBy().getEmployeeId());
        dataFetcher.getFromEmployeeServiceEmployeeWithId(requestDTO.getRequestedFor().getEmployeeId());
        dataFetcher.getFromAssetServiceAssetWithId(requestDTO.getRequestedAsset().getAssetId());
        return true;
    }
}
