package com.magneto.mutants.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.magneto.mutants.exceptions.handlers.RestExceptionHandler;
import com.magneto.mutants.rest.DnaChainRest;
import com.magneto.mutants.service.dto.DnaChainDTO;
import com.magneto.mutants.service.impl.DnaChainServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(DnaChainRest.class)
public class DnaChainRestTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private DnaChainServiceImpl dnaService;
	
	@Autowired
	RestExceptionHandler restExceptionHandler;
	
	@Test
	public void isMutantTest() throws Exception {
		DnaChainDTO chainDTO = new DnaChainDTO();
		chainDTO.setDna(Arrays.asList("ATGCGA","CAGTGC","TTCTGT","AGAAGG","CCACTA","TCACTG"));
		
		Gson gson = new Gson();
        String json = gson.toJson(chainDTO);
        
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mutant")
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
		
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		assertEquals("false", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void getStats() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/stats");
		
		MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
		assertEquals("No existen estadisticas previas", 
				mvcResult.getResponse().getContentAsString());
			
	}
}
