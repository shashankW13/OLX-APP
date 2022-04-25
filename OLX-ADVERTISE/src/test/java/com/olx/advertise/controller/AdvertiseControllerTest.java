package com.olx.advertise.controller;
 
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

import com.olx.controller.AdvertiseController;
import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;
/* 
@WebMvcTest(AdvertiseController.class)
class AdvertiseControllerTest {
 
    @Autowired
    MockMvc  mockMvc;

    @Autowired
    AdvertiseController advertiseController;

    @MockBean
    AdvertiseService  advertiseService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateNewAdvertise() throws Exception
    {
        AdvertiseDTO advertise = new AdvertiseDTO();
        advertise.setTitle("Sofa Sale");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "D78KL");

        when(this.advertiseService.createNewAdvertise(advertise, "D78KL")).thenReturn(advertise);

        MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:9093/advertises/")
        		.contentType("application/json")
                .content(objectMapper.writeValueAsString(advertise))
                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sofa")))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString(); 
        assertEquals(response.contains("Sofa"), true);

    }

    @Test
    public void testSearchAdvertisesByFilterCriteria() throws Exception{
        List<AdvertiseDTO> advertiseList = new ArrayList<AdvertiseDTO>();
        advertiseList.add(new AdvertiseDTO());
        advertiseList.add(new AdvertiseDTO());
        when(this.advertiseService.filterAdvertise(null, 0, null, null, null, null, null, null, 0, 10)).thenReturn(advertiseList);

        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:9093/advertises/search/filtercriteria")
                .param("category", "0").param("startIndex", "0"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response.contains("title"), true);
    }
    
    
    @Test
    public void testgetAllAdvertises() throws Exception{
        List<AdvertiseDTO> advertiseList = new ArrayList<AdvertiseDTO>();
        advertiseList.add(new AdvertiseDTO(1,"User", "Hello boss", 45.67, 3,8, "SurajSandhu123"));
        advertiseList.add(new AdvertiseDTO(2,"User23", "Hello bi", 45.67, 3,8, "SurajSandhu123"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "D78KL");
        when(this.advertiseService.getAllAdvertises("D78FA")).thenReturn(advertiseList);

        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:9093/advertises/user/advertise")
        		.contentType("application/json")
                .content(objectMapper.writeValueAsString(advertiseList))
                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(content().string(containsString("User")))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response.contains("User"), true);
    }
 
}*/