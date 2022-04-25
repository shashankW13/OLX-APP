package com.olx.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class LoginServiceDelegateImpl implements LoginServiceDelegate{

	@Autowired
	RestTemplate restTemplate;
	
	@CircuitBreaker(name = "AUTH_TOKEN_VALIDATION", 
			fallbackMethod = "fallBackIsTokenValid")
	@Override
	public boolean isTokenValid(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> response = this.restTemplate
		.exchange("http://API-GATEWAY/olx/login/token/validate", 
				HttpMethod.GET, entity, Boolean.class);
		
		return response.getBody();
	}
	
	public Boolean fallbackCallMethod(String authToken, Exception ex) {
		System.out.println("Login-OLX failed inside fallback function  " + ex);
		return false;
	}
	/*
	@Override
	public String getUsername(String authToken) {
		String plainCreds = "shadow:abcd";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic" + base64Creds);
		//headers.set("Authorization", authToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Json> response = this.restTemplate
		.exchange("http://localhost:5000/olx/login/user", 
				HttpMethod.GET, entity, Json.class);	
		
		Json response1 = response.getBody();
		
		return response.getBody().toString();
	}
*/
	public void ResponseConverter(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}

	@Override
	public Object getUsername(String authToken) {

		String url = "http://localhost:5000/olx/login/user";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authToken);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
	//	ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
	//	RestTemplate restTemplate1 = new RestTemplate(requestFactory);
		
	/*	ResponseEntity<String> response = restTemplate1
		.exchange("http://localhost:5000/olx/login/user", 
				HttpMethod.GET, entity, String.class);	*/
		Object res = restTemplate
				.getForObject(url, List.class, entity);
		
		return res;
		//String res = string;
		
		//return res;
	}
	
}
