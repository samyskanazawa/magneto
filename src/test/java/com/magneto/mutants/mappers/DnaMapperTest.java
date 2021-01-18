package com.magneto.mutants.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.magneto.mutants.Constants;
import com.magneto.mutants.mappers.DnaMapper;
import com.magneto.mutants.repository.data.DnaChain;
import com.magneto.mutants.service.dto.DnaChainDTO;

public class DnaMapperTest {
	
	@Test
	public void toEntity() {
		DnaChainDTO dnaChainDTO = new DnaChainDTO();
		List<String> chainList = Arrays.asList("ATGCGA","CACTGC","TCATCG","CGAAGG","CCCATC","TCGCTG");
		dnaChainDTO.setDna(chainList);
		StringBuilder chain = new StringBuilder();
		for(String dnaChain : chainList) {
			chain.append(dnaChain);
		}
		
		DnaChain dnaChain = DnaMapper.toEntity(dnaChainDTO, true, chain.toString());
		
		assertEquals(chainList.size(), dnaChain.getLength());
		assertEquals(Constants.VALID, dnaChain.getRemoved());
		assertEquals(Constants.DNA_CHAIN,dnaChain.getDna());
		assertEquals(true, dnaChain.getIsMutant());
	}
	
	@Test
	public void toDTO() {
		DnaChain chain = new DnaChain();
		chain.setDna(Constants.DNA_CHAIN);
		chain.setDna_id(1L);
		chain.setIsMutant(true);
		chain.setLength(Constants.DNA_LENGHT);
		chain.setRecording(new Date());
		chain.setRemoved(Constants.VALID);
		List<String> chainList = Arrays.asList("ATGCGA","CACTGC","TCATCG","CGAAGG","CCCATC","TCGCTG");
		
		DnaChainDTO chainDTO = DnaMapper.toDto(chain);
		
		assertEquals(chainList, chainDTO.getDna());
	}


}
