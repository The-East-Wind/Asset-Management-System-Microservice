package com.cg.assetmanagementsystem.assetservice.service;

import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import com.cg.assetmanagementsystem.assetservice.exception.AssetDeletionException;
import com.cg.assetmanagementsystem.assetservice.exception.AssetNotFoundException;
import com.cg.assetmanagementsystem.assetservice.exception.ReportGenerationException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface AssetService {
    List<Asset> getAllAssets();
    List<Asset> getAllAssetsByCategory(String assetCategory);
    Asset getAssetById(int assetId) throws AssetNotFoundException;
    Asset addNewAsset(Asset newAsset);
    Asset deleteAsset(Integer assetId) throws AssetNotFoundException, AssetDeletionException;
    Asset updateAssetDetails(Asset updatedAsset) throws AssetNotFoundException;
    ByteArrayInputStream generateAssetReport() throws ReportGenerationException;
}
