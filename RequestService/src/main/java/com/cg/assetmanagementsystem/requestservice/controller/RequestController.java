package com.cg.assetmanagementsystem.requestservice.controller;

import com.cg.assetmanagementsystem.requestservice.dto.RequestDTO;
import com.cg.assetmanagementsystem.requestservice.entity.Request;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import com.cg.assetmanagementsystem.requestservice.exception.ReportGenerationException;
import com.cg.assetmanagementsystem.requestservice.exception.RequestNotFoundException;
import com.cg.assetmanagementsystem.requestservice.service.RequestService;
import com.cg.assetmanagementsystem.requestservice.util.Mapper;
import com.cg.assetmanagementsystem.requestservice.util.Validator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
@CrossOrigin
@Api(value = "Asset Management System Request Service",produces = "application/json")
public class RequestController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Validator validator;
    @ApiOperation(value = "GET all pending requests.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Pending Requests fetched successfully."),
            @ApiResponse(code = 400,message = "No data found in other service."),
            @ApiResponse(code = 500,message = "Fetching data from other services failed.")
    })
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public List<RequestDTO> getPendingRequests() throws InvalidRequestException{
        List<Request> pendingRequests = requestService.getPendingRequests();
        List<RequestDTO> pendingRequestDTOs = new ArrayList<>();
        for(Request pendingRequest:pendingRequests){
            pendingRequestDTOs.add(mapper.entityToDTO(pendingRequest));
        }
        return pendingRequestDTOs;
    }
    @ApiOperation(value = "GET request with give ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Request fetched successfully."),
            @ApiResponse(code = 404,message = "No request was found with the given ID."),
            @ApiResponse(code = 500,message = "Fetching data from other services failed.")
    })
    @GetMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public RequestDTO getRequestWithId(@ApiParam(value = "ID of the request you wish to fetch",required = true) @PathVariable("id") Integer requestId) throws RequestNotFoundException,InvalidRequestException {
        Request requestWithId = requestService.getRequestWithId(requestId);
        return mapper.entityToDTO(requestWithId);
    }
    @ApiOperation(value = "Add a new request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Added successfully"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 500,message = "Fetching data from other services failed.")
    })
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public RequestDTO addNewRequest(@ApiParam(value = "The new request to be added.",required = true) @RequestBody RequestDTO newRequest) throws InvalidRequestException {
        validator.validateRequest(newRequest);
        Request newRequestToBeAdded = mapper.dtoToEntity(newRequest);
        newRequest =  mapper.entityToDTO(requestService.addNewRequest(newRequestToBeAdded));
        return newRequest;
    }
    @ApiOperation(value = "Update details of existing request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Added successfully"),
            @ApiResponse(code = 400, message = "Invalid request body/ Id in url path didn't match with id in body of request"),
            @ApiResponse(code = 500,message = "Fetching data from other services failed.")
    })
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public RequestDTO updateRequest(
            @ApiParam(value = "ID of the request whose details are to be modified.",required = true)
            @PathVariable("id") Integer requestId,
            @ApiParam(value = "The updated request.",required = true)
            @RequestBody RequestDTO updatedRequest
    ) throws RequestNotFoundException,InvalidRequestException {
        if(requestId!=updatedRequest.getRequestId()){
            throw new InvalidRequestException("Id in url path does not match id in request body.");
        }
        validator.validateRequest(updatedRequest);
        Request requestToBeUpdated = mapper.dtoToEntity(updatedRequest);
        return mapper.entityToDTO(requestService.updateRequest(requestToBeUpdated));
    }
    @ApiOperation(value = "GET report with details about request in the form of an Excel file.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Report Generated Successfully"),
            @ApiResponse(code = 500,message = "Report generation failed")
    })
    @GetMapping(
            value = "/report"
    )
    public ResponseEntity<InputStreamResource> getRequestReport() throws ReportGenerationException {
        ByteArrayInputStream in = requestService.generateRequestReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=request_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}

