package com.cg.assetmanagementsystem.assetservice.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @Column(name = "asset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assetId;
    @Column(name = "asset_name")
    private String assetName;
    @Column(name = "asset_description")
    private String assetDescription;
    @Column(name = "asset_category")
    private String assetCategory;
    @Column(name = "availability")
    private String availability;
    @Column(name = "allotted_to")
    private Integer allottedTo;

    public Asset() {
    }

    public Asset(String assetName, String assetDescription, String assetCategory, String availability, Integer allottedTo) {
        this.assetName = assetName;
        this.assetDescription = assetDescription;
        this.assetCategory = assetCategory;
        this.availability = availability;
        this.allottedTo = allottedTo;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
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

    public Integer getAllottedTo() {
        return allottedTo;
    }

    public void setAllottedTo(Integer allottedTo) {
        this.allottedTo = allottedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset asset = (Asset) o;
        return getAssetId() == asset.getAssetId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetId());
    }
}
