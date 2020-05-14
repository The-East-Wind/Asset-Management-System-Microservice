package com.cg.assetmanagementsystem.requestservice.service;

import com.cg.assetmanagementsystem.requestservice.dao.RequestDAO;
import com.cg.assetmanagementsystem.requestservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.requestservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.requestservice.entity.Request;
import com.cg.assetmanagementsystem.requestservice.exception.DataFetchException;
import com.cg.assetmanagementsystem.requestservice.exception.InvalidRequestException;
import com.cg.assetmanagementsystem.requestservice.exception.ReportGenerationException;
import com.cg.assetmanagementsystem.requestservice.exception.RequestNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestDAO requestDAO;
    /*@Autowired
    private RestTemplate restTemplate;
    private final String employeeServiceURL = "http://employee-service/employees/";
    private final String assetServiceURL = "http://asset-service/assets/";*/

    public RequestServiceImpl() {
    }

    @Override
    public Request addNewRequest(Request newRequest) {
        return requestDAO.save(newRequest);
    }
    @Override
    public Request getRequestWithId(int requestId) throws RequestNotFoundException {
        Optional<Request> requestWithId = requestDAO.findById(requestId);
        if(!requestWithId.isPresent()){
            throw new RequestNotFoundException("No request was found with id:"+requestId+".");
        }
        return requestWithId.get();
    }

    @Override
    public List<Request> getPendingRequests() {
        return (List<Request>)requestDAO.findAllByStatusIgnoreCase("Pending");
    }

    @Override
    public Request updateRequest(Request updatedRequest) throws RequestNotFoundException {
        Request requestWithId = getRequestWithId(updatedRequest.getRequestId());
        return requestDAO.save(updatedRequest);
    }

    @Override
    public ByteArrayInputStream generateRequestReport() throws ReportGenerationException {
        Iterable<Request> requests = requestDAO.findAll();
        String[] headers = new String[]{"Id","Requested From","Requested Till","Requested By","Requested For","Requested Asset","Status"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Request Details");
        Row row = sheet.createRow(0);
        int cellnum = 0;
        for(String header:headers){
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(header);
        }
        List<String[]> requestData = new ArrayList<>();
        requests.forEach(request -> requestData.add(new String[]{
                request.getRequestId().toString(),
                request.getRequestedFrom().toString(),
                request.getRequestedTill().toString(),
                request.getRequestedBy().toString(),
                request.getRequestedFor().toString(),
                request.getRequestedAsset().toString(),
                request.getStatus()
        }));
        int rownum = 1;
        for(String[] request:requestData){
            cellnum = 0;
            row = sheet.createRow(rownum++);
            for(String cellData:request){
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(cellData);
            }
        }
        ByteArrayOutputStream bos;
        byte[] byteArrayData;
        try{
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byteArrayData = bos.toByteArray();
            bos.close();
        }
        catch (IOException exception){
            throw new ReportGenerationException("Error! Report generation failed",exception);
        }
        return new ByteArrayInputStream(byteArrayData);
    }
}
