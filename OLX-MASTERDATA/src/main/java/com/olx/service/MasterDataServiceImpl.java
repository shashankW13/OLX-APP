package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.MasterDataCategory;
import com.olx.dto.MasterDataStatus;
import com.olx.entity.MasterDataCategoryEntity;
import com.olx.entity.MasterDataStatusEntity;
import com.olx.repository.MasterDataCategoryRepo;
import com.olx.repository.MasterDataStatusRepo;

@Service
public class MasterDataServiceImpl implements MasterDataService{

	@Autowired
	MasterDataCategoryRepo masterDataCategoryRepo;
	
	@Autowired
	MasterDataStatusRepo masterDataStatusRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<MasterDataCategory> AdvertisementCategories() {
		List<MasterDataCategoryEntity> masterDataCategoryEntities = masterDataCategoryRepo.findAll();
		List<MasterDataCategory> masterDataCategories = convertEntityListIntoDTOList(masterDataCategoryEntities);
		return masterDataCategories;
	}

	@Override
	public List<MasterDataStatus> AdvertisementStatus() {
		List<MasterDataStatusEntity> masterDataStatusEntities = masterDataStatusRepo.findAll();
		List<MasterDataStatus> masterDataStatusList = convertEntityListIntoDTOList1(masterDataStatusEntities);
		return masterDataStatusList;
	}

	private List<MasterDataCategory> convertEntityListIntoDTOList(List<MasterDataCategoryEntity> masterDataEntities) {
		List<MasterDataCategory> masterDataCategoryList = new ArrayList<MasterDataCategory>();
			for(MasterDataCategoryEntity masterDataCategoryEntity: masterDataEntities) {
				MasterDataCategory masterDataDto = convertEntityIntoDTO(masterDataCategoryEntity);
				masterDataCategoryList.add(masterDataDto);
			}
		return masterDataCategoryList;
	}
	
	private List<MasterDataStatus> convertEntityListIntoDTOList1(List<MasterDataStatusEntity> masterDataStatusEntities) {
		List<MasterDataStatus> masterDataStatusList = new ArrayList<MasterDataStatus>();
			for(MasterDataStatusEntity masterDataStatusEntity: masterDataStatusEntities) {
				MasterDataStatus masterDataStatus = convertEntityIntoDTO1(masterDataStatusEntity);
				masterDataStatusList.add(masterDataStatus);
			}
		return masterDataStatusList;
	}
	/*
	private MasterDataCategoryEntity convertDTOIntoEntity(MasterDataCategory masterDataCategory) {
		MasterDataCategoryEntity masterDataCategoryEntity = modelMapper.map(masterDataCategory, MasterDataCategoryEntity.class);
		return masterDataCategoryEntity;
	}
	*/
	private MasterDataCategory convertEntityIntoDTO(MasterDataCategoryEntity masterDataCategoryEntity) {
		MasterDataCategory masterDataCategory = modelMapper.map(masterDataCategoryEntity, MasterDataCategory.class);
		return masterDataCategory;
	}
	/*
	private MasterDataStatusEntity convertDTOIntoEntity(MasterDataStatus masterDataStatus) {
		MasterDataStatusEntity masterDataStatusEntity = modelMapper.map(masterDataStatus, MasterDataStatusEntity.class);
		return masterDataStatusEntity;
	}
	*/
	private MasterDataStatus convertEntityIntoDTO1(MasterDataStatusEntity masterDataStatusEntity) {
		MasterDataStatus masterDataStatus = modelMapper.map(masterDataStatusEntity, MasterDataStatus.class);
		return masterDataStatus;
	}

	
}