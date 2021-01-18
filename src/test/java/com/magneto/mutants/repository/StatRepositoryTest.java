package com.magneto.mutants.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.magneto.mutants.repository.data.Stat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatRepositoryTest {

	@Autowired
	private StatRepository statRepository;
	
	@Before
	public void setUp() {
		Stat stat = new Stat();
		stat.setCountHuman(100);
		stat.setCountMutant(40);
		stat.setRatio(0.4);
		
		statRepository.save(stat);
	}
	
	@Test
	public void findAll() {
		List<Stat> stats = statRepository.findAll();
		assertEquals(1, stats.size());
	}
	
}
