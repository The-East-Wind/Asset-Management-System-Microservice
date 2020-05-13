package com.cg.assetmanagementsystem.requestservice.service;

import com.cg.assetmanagementsystem.requestservice.entity.Request;
import com.cg.assetmanagementsystem.requestservice.exception.ReportGenerationException;
import com.cg.assetmanagementsystem.requestservice.exception.RequestNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface RequestService {
    Request addNewRequest(Request newRequest);
    Request getRequestWithId(int requestId) throws RequestNotFoundException;
    List<Request> getPendingRequests();
    Request updateRequest(Request updatedRequest) throws RequestNotFoundException;
    ByteArrayInputStream generateRequestReport() throws ReportGenerationException;
}
