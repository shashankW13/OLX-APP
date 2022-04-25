package com.olx.service;

import java.time.LocalDate;
import java.util.List;


import com.olx.dto.AdvertiseDTO;

public interface AdvertiseService {

	//8
	AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken);
	
	//9
	AdvertiseDTO updateExistingAdvertisement(AdvertiseDTO updatedAdvertiseDto, String authToken, int id);
	
	//10
	List<AdvertiseDTO> getAllUserAdvertisements(String authToken);
	
	//11
	AdvertiseDTO getUserAdvertisementById(String authToken, int id);
	
	//12
	boolean deleteAdvertisementById(String authToken, int id);
	
	//13
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(String searchText,
			int categoryId, String postedBy, String dateCondition,
			LocalDate onDate, LocalDate fromDate, LocalDate toDate,
			String sortedBy, int startIndex, int records);
	
	//14
	List<AdvertiseDTO> searchAdvertiseByText(String searchText);
	
	//15
	List<AdvertiseDTO> getAdvertisementDetails(String authToken, int id);

	
	
	

	
}
