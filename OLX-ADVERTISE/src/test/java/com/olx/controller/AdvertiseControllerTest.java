package com.olx.controller;
 
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
 
import com.fasterxml.jackson.databind.ObjectMapper;

import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;
 
@WebMvcTest(AdvertiseController.class)
class AdvertiseController1Test {
 
    @Autowired
    MockMvc  mockMvc;

    @Autowired
    AdvertiseController advertiseController;

    @MockBean
    AdvertiseService  advertiseService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateNewAdvertise() throws Exception
    {
        AdvertiseDTO advertiseDto = new AdvertiseDTO();
        advertiseDto.setTitle("Mobile Sale");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "A87TH");

        when(this.advertiseService.createNewAdvertise(advertiseDto, "A87TH"))
        .thenReturn(advertiseDto);

        MvcResult mvcResult = this.mockMvc.perform(
        		post("http://localhost:5002/olx/advertise/")
        		.contentType("application/json")
                .content(objectMapper.writeValueAsString(advertiseDto))
                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("Mobile")))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString(); 
        assertEquals(true, response.contains("Mobile"));

    }

    @Test
    void testSearchAdvertisesByFilterCriteria() throws Exception{
        List<AdvertiseDTO> advertiseList = new ArrayList<AdvertiseDTO>();
        advertiseList.add(new AdvertiseDTO());
        advertiseList.add(new AdvertiseDTO());
        when(this.advertiseService
        		.searchAdvertisesByFilterCriteria(null, 0, null, null, null, null, null, null, 0, 10))
        .thenReturn(advertiseList);

        MvcResult mvcResult = this.mockMvc.perform(
        		get("http://localhost:5002/olx/advertise/search/filtercriteria")
                .param("category", "0").param("startIndex", "0"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(true, response.contains("title"));
    }
    
    
    @Test
    void testgetAllAdvertises() throws Exception{
        List<AdvertiseDTO> advertiseList = new ArrayList<AdvertiseDTO>();
        advertiseList
        .add(new AdvertiseDTO(1, "User", "Mobile Sale", 25000, 3, null, 1, null, null, null, false, "shadow"));
        
        advertiseList
        .add(new AdvertiseDTO(1, "User", "Car sale", 500000, 2, null, 1, null, null, null, false, "naruto"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "A78TH");
        when(this.advertiseService.getAllUserAdvertisements("A78TH")).thenReturn(advertiseList);

        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:5002/olx/advertise/")
        		.contentType("application/json")
                .content(objectMapper.writeValueAsString(advertiseList))
                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("User")))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(true, response.contains("User"));
    }
 



}