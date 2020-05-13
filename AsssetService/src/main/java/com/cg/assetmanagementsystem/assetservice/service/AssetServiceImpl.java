package com.cg.assetmanagementsystem.assetservice.service;

import com.cg.assetmanagementsystem.assetservice.dao.AssetDAO;
import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import com.cg.assetmanagementsystem.assetservice.exception.AssetDeletionException;
import com.cg.assetmanagementsystem.assetservice.exception.AssetNotFoundException;
import com.cg.assetmanagementsystem.assetservice.exception.ReportGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
@Transactional
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetDAO assetDAO;
    @Override
    public List<Asset> getAllAssets() {
        return (List<Asset>)assetDAO.findAllByAvailabilityIsNot("Deleted");
    }

    @Override
    public List<Asset> getAllAssetsByCategory(String assetCategory) {
        return ((List<Asset>)assetDAO.findAllByAssetCategoryIgnoreCase(assetCategory)).stream()
                .filter(asset -> {
                    return !asset.getAvailability().equals("Deleted");
                })
                .collect(Collectors.toList());
    }

    @Override
    public Asset getAssetById(int assetId) throws AssetNotFoundException {
        Optional<Asset> assetOptional = assetDAO.findById(assetId);
        if(!assetOptional.isPresent()||assetOptional.get().getAvailability().equals("Deleted")){
            throw new AssetNotFoundException("No asset found with id:"+assetId+".");
        }
        return assetOptional.get();
    }

    @Override
    public Asset addNewAsset(Asset newAsset) {
        return assetDAO.save(newAsset);
    }

    @Override
    public Asset deleteAsset(Integer assetId) throws AssetNotFoundException,AssetDeletionException {
        Optional<Asset> assetOptional = assetDAO.findById(assetId);
        if(!assetOptional.isPresent()||assetOptional.get().getAvailability().equals("Deleted"))
            throw new AssetNotFoundException("No asset found with the id:"+assetId+".");
        if(assetOptional.get().getAvailability().equals("Not Available"))
            throw new AssetDeletionException("Cannot Delete Asset.The Asset you are trying to delete is currently allotted to someone.");
        Asset toBeDeleted = assetOptional.get();
        toBeDeleted.setAvailability("Deleted");
        return assetDAO.save(toBeDeleted);
    }

    @Override
    public Asset updateAssetDetails(Asset updatedAsset) throws AssetNotFoundException {
        Optional<Asset> assetOptional = assetDAO.findById(updatedAsset.getAssetId());
        if(!assetOptional.isPresent()){
            throw new AssetNotFoundException("No asset found with id:"+updatedAsset.getAssetId()+".");
        }
        return assetDAO.save(updatedAsset);
    }
    @Override
    public ByteArrayInputStream generateAssetReport() throws ReportGenerationException {
        Iterable<Asset> allAssets = assetDAO.findAll();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Asset Details");
        String headers[] = new String[]{"Id","Name","Description","Category","Availability","Allotted To"};
        Row row = sheet.createRow(0);
        int cellnum=0;
        for(String header: headers){
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(header);
        }
        int rownum = 1;
        List<String[]> assetData = new ArrayList<>();
        allAssets.forEach(asset-> assetData.add(new String[]{
                asset.getAssetId().toString(),
                asset.getAssetName(),
                asset.getAssetDescription(),
                asset.getAssetCategory(),
                asset.getAvailability(),
                asset.getAllottedTo()!= null?asset.getAllottedTo().toString():""
        }));
        for(String[] asset:assetData){
            cellnum=0;
            row = sheet.createRow(rownum++);
            for(String data:asset){
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(data);
            }
        }
        ByteArrayOutputStream bos;
        byte[] byteArrayData;
        try {
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byteArrayData = bos.toByteArray();
            bos.close();
        }
        catch(IOException exception){
            throw new ReportGenerationException("An error occurred when generating Asset Report",exception);
        }
        return new ByteArrayInputStream(byteArrayData);
    }
}
