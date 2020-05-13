package com.cg.assetmanagementsystem.requestservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(description = "Details of request for asset")
public class RequestDTO {
    @ApiModelProperty(notes = "Database generated Id for request")
    private Integer requestId;
    @ApiModelProperty(notes = "Date from which asset is requested")
    private LocalDate requestedFrom;
    @ApiModelProperty(notes = "Date till which asset is requested")
    private LocalDate requestedTill;
    @ApiModelProperty(notes = "Employee by whom the request is made")
    private EmployeeDTO requestedBy;
    @ApiModelProperty(notes = "Employee for whom the asset is requested")
    private EmployeeDTO requestedFor;
    @ApiModelProperty(notes = "The Asset which is requested")
    private AssetDTO requestedAsset;
    @ApiModelProperty(notes = "Current Status of the request")
    private String status;

    public RequestDTO() {
    }

    public RequestDTO(Integer requestId, LocalDate requestedFrom, LocalDate requestedTill, String status) {
        this.requestId = requestId;
        this.requestedFrom = requestedFrom;
        this.requestedTill = requestedTill;
        this.status = status;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public LocalDate getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(LocalDate requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public LocalDate getRequestedTill() {
        return requestedTill;
    }

    public void setRequestedTill(LocalDate requestedTill) {
        this.requestedTill = requestedTill;
    }

    public EmployeeDTO getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(EmployeeDTO requestedBy) {
        this.requestedBy = requestedBy;
    }

    public EmployeeDTO getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(EmployeeDTO requestedFor) {
        this.requestedFor = requestedFor;
    }

    public AssetDTO getRequestedAsset() {
        return requestedAsset;
    }

    public void setRequestedAsset(AssetDTO requestedAsset) {
        this.requestedAsset = requestedAsset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
