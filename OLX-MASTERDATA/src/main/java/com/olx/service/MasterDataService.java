package com.olx.service;

import java.util.List;

import com.olx.dto.MasterDataCategory;
import com.olx.dto.MasterDataStatus;

public interface MasterDataService {
	
	public List<MasterDataCategory> AdvertisementCategories();
	public List<MasterDataStatus> AdvertisementStatus();
}