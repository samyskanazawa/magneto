package com.magneto.mutants.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.magneto.mutants.repository.DnaRepository;
import com.magneto.mutants.repository.StatRepository;
import com.magneto.mutants.service.dto.DnaChainDTO;
import com.magneto.mutants.service.dto.StatDTO;
import com.magneto.mutants.service.impl.DnaChainServiceImpl;

@RunWith(SpringRunner.class)
public class DnaChainServiceTest {
	
	@Mock
	DnaRepository dnaRepository = mock(DnaRepository.class);
	
	@Mock
	StatRepository statRepository = mock(StatRepository.class);
	
	@InjectMocks
	private DnaChainServiceImpl serviceChainService = new DnaChainServiceImpl();
	
	
	@Test
	public void isMutantTest() {
		DnaChainDTO dnaChainDTO = new DnaChainDTO();
		dnaChainDTO.setDna(Arrays.asList("ATGCGA","CACTGC","TCATCG","CGAAGG","CCCATC","TCGCTG"));
						
		boolean isMutant = serviceChainService.isMutant(dnaChainDTO);
		assertEquals(true, isMutant);
	}
	
	@Test
	public void getStatsEmptyTest() {
		StatDTO statDTO = mock(StatDTO.class);
		
		statDTO = serviceChainService.getStats();
		assertEquals(null, statDTO);
	}

}
