package com.cg.assetmanagementsystem.assetservice.dao;

import com.cg.assetmanagementsystem.assetservice.entity.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetDAO extends CrudRepository<Asset,Integer> {
    Iterable<Asset> findAllByAssetCategoryIgnoreCase(String assetCategory);
    Iterable<Asset> findAllByAvailabilityIsNot(String availability);
}
