package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterDelegateServiceImpl implements MasterDataDelegate{
	
	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String getCategoryDescription(int categoryId, String authToken) {
		
		if(loginServiceDelegate.isTokenValid(authToken)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);	
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<String> response = 
					restTemplate.exchange(
							"http://API-GATEWAY/olx/masterData/category/" 
					+ categoryId, HttpMethod.GET, entity, String.class);
			return response.getBody();
			
		}
		return null;
	}
	@Override
	public String getStatusName(int statusId, String authToken) {

		if(loginServiceDelegate.isTokenValid(authToken)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);	
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<String> response = 
					restTemplate
					.exchange(
							"http://API-GATEWAY/olx/masterData/status/" 
					+ statusId, HttpMethod.GET, entity, String.class);
			return response.getBody();
			
		}
		return null;
	}
	
}
