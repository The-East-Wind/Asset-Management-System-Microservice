package com.cg.assetmanagementsystem.requestservice.util;

import com.cg.assetmanagementsystem.requestservice.dto.RequestDTO;
import com.cg.assetmanagementsystem.requestservice.entity.Request;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    /*@Autowired
    private RequestServiceImpl requestService;*/
    @Autowired
    private DataFetcher dataFetcher;
    public  RequestDTO entityToDTO(Request request) throws InvalidRequestException {
        RequestDTO requestDTO = new RequestDTO(
                request.getRequestId(),
                request.getRequestedFrom(),
                request.getRequestedTill(),
                request.getStatus()
        );
        requestDTO.setRequestedFor(dataFetcher.getFromEmployeeServiceEmployeeWithId(request.getRequestedFor()));
        requestDTO.setRequestedBy(dataFetcher.getFromEmployeeServiceEmployeeWithId(request.getRequestedBy()));
        requestDTO.setRequestedAsset(dataFetcher.getFromAssetServiceAssetWithId(request.getRequestedAsset()));
        return requestDTO;
    }
    public Request dtoToEntity(RequestDTO requestDTO){
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
