package com.cg.assetmanagementsystem.requestservice.util;

import com.cg.assetmanagementsystem.requestservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.requestservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.requestservice.dto.RequestDTO;
import com.cg.assetmanagementsystem.requestservice.entity.Request;
import com.cg.assetmanagementsystem.requestservice.exception.DataFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Mapper {
    private static String employeeServiceURL = "http://employee-service/employees/";
    private static String assetServiceURL = "http://asset-service/assets/";
    public static RequestDTO entityToDTO(Request request,RestTemplate restTemplate){
        RequestDTO requestDTO = new RequestDTO(
                request.getRequestId(),
                request.getRequestedFrom(),
                request.getRequestedTill(),
                request.getStatus()
        );
        ResponseEntity<EmployeeDTO> responseEntityEmployee;
        ResponseEntity<AssetDTO> responseEntityAsset;
        try {
            responseEntityEmployee = restTemplate.getForEntity(employeeServiceURL + request.getRequestedFor(), EmployeeDTO.class);
            requestDTO.setRequestedFor(responseEntityEmployee.getBody());
            responseEntityEmployee = restTemplate.getForEntity(employeeServiceURL+request.getRequestedBy(),EmployeeDTO.class);
            requestDTO.setRequestedBy(responseEntityEmployee.getBody());
            responseEntityAsset = restTemplate.getForEntity(assetServiceURL+request.getRequestedAsset(),AssetDTO.class);
            requestDTO.setRequestedAsset(responseEntityAsset.getBody());
        }
        catch (HttpClientErrorException exception){
            throw new DataFetchException("An error occurred when fetching data",exception);
        }
        return requestDTO;
    }
    public static Request dtoToEntity(RequestDTO requestDTO){
        Request request = new Request(
                requestDTO.getRequestedFrom(),
                requestDTO.getRequestedTill(),
                requestDTO.getRequestedBy().getEmployeeId(),
                requestDTO.getRequestedFor().getEmployeeId(),
                requestDTO.getRequestedAsset().getAssetId(),
                requestDTO.getStatus()
        );
        request.setRequestId(requestDTO.getRequestId());
        return request;
    }
}
