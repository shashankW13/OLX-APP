package com.olx.controller;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.http.HttpHeaders;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;

@WebMvcTest(AdvertiseControllerTest.class)
class AdvertiseControllerTest {

	//Heart to RESTful testing
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdvertiseController advertiseController;
	
	@MockBean
	AdvertiseService advertiseService;
	/*
	@Test
	public void testCreateNewAdvertise() throws Exception{
		AdvertiseDTO advertiseDto = new AdvertiseDTO();
		advertiseDto.setTitle("Sofa Sale");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "D78KL");
		
		when(this.advertiseService.createNewAdvertise(anything()), any(String.class))
			.thenReturn(advertiseDto);
		
		MvcResult mvcResult = this.mockMvc.perform(
				post("http://localhost:8080/olx/advertise/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(advertiseDto))
				.headers(httpHeaders)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Sofa")))
				.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Sofa"), true);

	}
	
	@Test
	public void testSearchAdvertisesByFilterCriteria() {
		List<AdvertiseDTO> advertiseDTOList = new List<AdvertiseDTO>();
		advertiseDTOList.add(new AdvertiseDTO());
		advertiseDTOList.add(new AdvertiseDTO());
		when(this.advertiseService.searchAdvertisesByFilterCriteria(
				null, 0, null, null, null, null, null, null, 0, 10))
					.thenReturn(advertiseDTOList);
		
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8080/olx/advertise/search/filtercriteria"))
				.param("category", "1")
				.param("startIndex", "0")
				.andExpect(status().isOk())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("title"), true);
		
			
	}
*/
}
