package com.magneto.mutants.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

import com.magneto.mutants.mappers.StatMapper;
import com.magneto.mutants.repository.data.Stat;
import com.magneto.mutants.service.dto.StatDTO;

public class StatMapperTest {
	
	@Test
	public void toEntity() {
		StatDTO statDTO = new StatDTO();
		statDTO.setCount_human_dna(100);
		statDTO.setCount_mutant_dna(40);
		statDTO.setRatio(0.4);
		
		Stat stat = StatMapper.toEntity(statDTO);
		
		assertEquals(100, stat.getCountHuman());
		assertEquals(40, stat.getCountMutant());
		assertEquals(0.4,stat.getRatio());
	}
	
	@Test
	public void toDTO() {
		Stat stat = new Stat();
		stat.setCountHuman(100);
		stat.setCountMutant(40);
		stat.setRatio(0.4);
		
		StatDTO statDTO = StatMapper.toDto(stat);
		
		assertEquals(100, statDTO.getCount_human_dna());
		assertEquals(40, statDTO.getCount_mutant_dna());
		assertEquals(0.4,statDTO.getRatio());
	}
}
