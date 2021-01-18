package com.magneto.mutants.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.magneto.mutants.Constants;
import com.magneto.mutants.repository.data.DnaChain;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DnaRepositoryTest {

	@Autowired
	private DnaRepository dnaRepository;
	
	@Before
	public void setUp() {
		DnaChain dnaChain = new DnaChain();
		dnaChain.setDna(Constants.DNA_CHAIN);
		dnaChain.setIsMutant(true);
		dnaChain.setLength(Constants.DNA_LENGHT);
		dnaChain.setRecording(new Date());
		dnaChain.setRemoved(0);
		
		dnaRepository.save(dnaChain);
	}
	
	@Test
	public void findAllTest() {
		List<DnaChain> dnaChains = dnaRepository.findAll();
		assertEquals(1, dnaChains.size());
	}
	
	@Test
	public void findDnaChainByDna() {
		DnaChain dnaChain = dnaRepository.findDnaChainByDna(Constants.DNA_CHAIN);
		assertEquals(Constants.DNA_CHAIN, dnaChain.getDna());
	}
	
}
