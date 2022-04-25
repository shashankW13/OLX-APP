package com.olx.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/olx/advertise")
@CrossOrigin(origins = "*")
public class AdvertiseController {

	@Autowired
	AdvertiseService advertiseService;
	
	//8
	@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public AdvertiseDTO newAdvertisement(@RequestBody AdvertiseDTO advertiseDTO,
			@RequestHeader("Authorization") String authToken){
		return advertiseService.createNewAdvertise(advertiseDTO, authToken);
	}
	//9
	@PutMapping(value = "/advertise/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AdvertiseDTO> updateExistingAdvertisement(@RequestBody AdvertiseDTO updatedAdvertiseDto,
			@RequestHeader("Authorization") String authToken,
			@PathVariable("id")int id){
		AdvertiseDTO advertiseDTO = this.advertiseService.updateExistingAdvertisement(updatedAdvertiseDto, authToken, id);
		return new ResponseEntity<>(advertiseDTO, HttpStatus.OK);
	}
	
	//10
	@GetMapping(value = "/user/advertise",
			produces = {MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<AdvertiseDTO>> getAllUserAdvertisements(
			@RequestHeader("Authorization") String authToken){
		List<AdvertiseDTO> advertiseDTOList = this.advertiseService.getAllUserAdvertisements(authToken);
		return new ResponseEntity<>(advertiseDTOList, HttpStatus.OK);
	}
	
	//11
	@GetMapping(value = "/user/advertise/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AdvertiseDTO> getUserAdvertisementById(
			@RequestHeader("Authorization") String authToken,
			@PathVariable("id") int id){
		AdvertiseDTO advertiseDTO = this.advertiseService.getUserAdvertisementById(authToken, id);
		return new ResponseEntity<>(advertiseDTO, HttpStatus.OK);
	}
	
	//12
	@DeleteMapping(value = "/user/advertise/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> deleteUserAdvertisementById(
			@RequestHeader("Authorization") String authToken,
			@PathVariable("id") int id){
		boolean advertiseDTO = this.advertiseService.deleteAdvertisementById(authToken, id);
		return new ResponseEntity<>(advertiseDTO, HttpStatus.OK);
	}
	
	//13
	@GetMapping(value="/search/filtercriteria", 
			produces={MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<AdvertiseDTO> searchAdvertisesByFilterCriteria(
	@RequestParam(name="searchText", required = false)String searchText,
	@RequestParam(name = "category", required = false, defaultValue = "0")int categoryId, @RequestParam(name="postedBy", required=false)String postedBy,
	@RequestParam(name="dateCondition", required=false)String dateCondition,
	@RequestParam(name="onDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
	@RequestParam(name="fromDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	@RequestParam(name="toDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	@RequestParam(name="sortedBy", required=false)String sortedBy, @RequestParam(name = "startIndex", defaultValue="0")int startIndex, 
	@RequestParam(name="records", defaultValue = "10")int records
	) {
	return advertiseService.searchAdvertisesByFilterCriteria(searchText, categoryId, postedBy, dateCondition,
	onDate, fromDate, toDate, sortedBy, startIndex, records);
	}
	
	//14
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Advertise by Search Text",
	notes = "This Rest API will return an existing Advertise by Search Text")
	public ResponseEntity<List<AdvertiseDTO>> searchAdvertisementByText
	(@RequestParam(name = "searchText") String searchText) {
		return new ResponseEntity<>(
				advertiseService.searchAdvertiseByText(searchText),HttpStatus.OK);
	}
	
	//15
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<AdvertiseDTO>> getAdvertisementDetails(
			@RequestHeader("Authorization") String authToken,
			@PathVariable("id") int id){
		List<AdvertiseDTO> advertiseDTOList = 
				this.advertiseService.getAdvertisementDetails(authToken, id);
		return new ResponseEntity<>(advertiseDTOList, HttpStatus.OK);
	}
	
}
