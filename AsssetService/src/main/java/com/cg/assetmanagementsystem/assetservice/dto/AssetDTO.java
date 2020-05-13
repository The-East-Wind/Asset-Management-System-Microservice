package com.cg.assetmanagementsystem.assetservice.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details of the Asset")
public class AssetDTO {
    @ApiModelProperty(notes = "Database generated ID of Asset.")
    private int assetId;
    @ApiModelProperty(notes = "Name of the Asset")
    private String assetName;
    @ApiModelProperty(notes = "Description about the Asset")
    private String assetDescription;
    @ApiModelProperty(notes = "Category of the Asset")
    private String assetCategory;
    @ApiModelProperty(notes = "Availability of the Asset")
    private String availability;
    @ApiModelProperty(notes = "The Employee to whom the Asset is allotted to if any")
    private EmployeeDTO allottedTo;

    public AssetDTO() {
    }

    public AssetDTO(int assetId, String assetName, String assetDescription, String assetCategory, String availability) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetDescription = assetDescription;
        this.assetCategory = assetCategory;
        this.availability = availability;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public EmployeeDTO getAllottedTo() {
        return allottedTo;
    }

    public void setAllottedTo(EmployeeDTO allottedTo) {
        this.allottedTo = allottedTo;
    }
}
