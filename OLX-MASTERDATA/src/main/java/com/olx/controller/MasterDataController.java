package com.olx.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.MasterDataCategory;
import com.olx.dto.MasterDataStatus;
import com.olx.service.MasterDataService;

@RestController
@RequestMapping("/olx/masterdata")
@CrossOrigin(origins = "*")
public class MasterDataController {

	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping(value = "/ads/category",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public List<MasterDataCategory> getAdvertisementCategories() {
			return masterDataService.AdvertisementCategories();
	}
	
	@GetMapping(value = "/ads/status",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public List<MasterDataStatus> getAdvertisementStatus() {
			return masterDataService.AdvertisementStatus();
	}
	
}