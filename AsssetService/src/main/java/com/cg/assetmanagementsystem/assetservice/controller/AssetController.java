package com.cg.assetmanagementsystem.assetservice.controller;

import com.cg.assetmanagementsystem.assetservice.dto.AssetDTO;
import com.cg.assetmanagementsystem.assetservice.dto.EmployeeDTO;
import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import com.cg.assetmanagementsystem.assetservice.exception.AssetDeletionException;
import com.cg.assetmanagementsystem.assetservice.exception.AssetNotFoundException;
import com.cg.assetmanagementsystem.assetservice.exception.InvalidRequestException;
import com.cg.assetmanagementsystem.assetservice.exception.ReportGenerationException;
import com.cg.assetmanagementsystem.assetservice.service.AssetService;
import com.cg.assetmanagementsystem.assetservice.util.Mapper;
import com.cg.assetmanagementsystem.assetservice.util.Validator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/assets")
@CrossOrigin
@Api(value = "Asset Management System Asset Service", produces = "application/json")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(
            produces = "application/json"
    )
    @ApiOperation(value = "GET all assets in database.",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved Asset(s)"),
            @ApiResponse(code = 404,message = "No Asset Found wish the given ID")
    })
    public List<AssetDTO> getAllAssets(
            @ApiParam(value = "ID of the asset you wish to GET")
            @RequestParam(name = "id",required = false) Integer assetId,
            @ApiParam(value = "Category of the asset(s) you wish to GET")
            @RequestParam(name="category", required = false) String assetCategory
    ) throws AssetNotFoundException {
        List<Asset> assets;
        if(assetId!=null){
            assets = new ArrayList<>();
            assets.add(assetService.getAssetById(assetId));
        }
        else if(assetCategory!=null){
            assets = assetService.getAllAssetsByCategory(assetCategory);
        }
        else{
            assets = assetService.getAllAssets();
        }
        List<AssetDTO> assetDTOS = new ArrayList<>();
        for(Asset asset:assets){
            assetDTOS.add(Mapper.entityToDTO(asset,restTemplate));
        }
        return assetDTOS;
    }
    @ApiOperation(value = "GET Asset with ID specified in the path.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully retrieved Asset(s)"),
            @ApiResponse(code = 404,message = "No Asset Found with the given ID")
    })
    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public AssetDTO getAssetWithId(@ApiParam(value = "ID of the asset you wish to GET",required = true) @PathVariable("id") Integer assetId) throws AssetNotFoundException {
        Asset assetWithId =  assetService.getAssetById(assetId);
        return  Mapper.entityToDTO(assetWithId,restTemplate);
    }
    @ApiOperation(value = "Add a new Asset to the database.")
    @PostMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public AssetDTO addNewAsset(@ApiParam(value = "The Asset that you wish to add",required = true) @RequestBody AssetDTO newAssetDTO) throws InvalidRequestException{
        Validator.validateInputData(newAssetDTO,restTemplate);
        Asset newAsset = Mapper.dtoToEntity(newAssetDTO);
        newAsset =  assetService.addNewAsset(newAsset);
        return Mapper.entityToDTO(newAsset,restTemplate);
    }
    @ApiOperation(value = "Update the details of an existing Asset")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Updated the details successfully"),
            @ApiResponse(code = 400,message = "The id in the URL path and the request body do not match"),
            @ApiResponse(code = 404,message = "No Asset was found with the given ID")
    })
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public AssetDTO updateAsset(
            @ApiParam(value = "ID of the Asset whose details is to updated",required = true)
            @PathVariable("id") Integer assetId,
            @ApiParam(value = "The Updated Asset Details",required = true)
            @RequestBody AssetDTO modifiedAsset
    ) throws AssetNotFoundException, InvalidRequestException {
        if(assetId!=modifiedAsset.getAssetId()){
            throw new InvalidRequestException("The id in the url path and the body of the request does not match.");
        }
        Validator.validateInputData(modifiedAsset,restTemplate);
        Asset asset = Mapper.dtoToEntity(modifiedAsset);
        return Mapper.entityToDTO(assetService.updateAssetDetails(asset),restTemplate);
    }
    @ApiOperation(value = "DELETE an Asset in from the Database")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Asset deleted successfully."),
            @ApiResponse(code = 403,message = "Asset Cannot be deleted."),
            @ApiResponse(code = 404,message = "No Asset found with the ID given in the URL path.")
    })
    @DeleteMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public AssetDTO deleteAsset(@ApiParam(value = "ID of the Asset you wish to DELETE.",required = true) @PathVariable("id") Integer assetId) throws AssetNotFoundException, AssetDeletionException {
        return Mapper.entityToDTO(assetService.deleteAsset(assetId),restTemplate);
    }
    @ApiOperation(value = "GET a report containing details of all Assets in the Database in the form of an Excel File")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Report Generated Successfully"),
            @ApiResponse(code = 500,message = "Report generation failed")
    })
    @GetMapping(
            value = "/report"
    )
    public ResponseEntity<InputStreamResource> getAssetReport() throws ReportGenerationException {
        ByteArrayInputStream in = assetService.generateAssetReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=asset_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
