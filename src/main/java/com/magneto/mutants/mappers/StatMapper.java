package com.magneto.mutants.mappers;

import com.magneto.mutants.repository.data.Stat;
import com.magneto.mutants.service.dto.StatDTO;

/**
 * @author samys
 *
 */
public class StatMapper {

	/**
	 * @param statDto
	 * @return
	 */
	public static Stat toEntity(StatDTO statDto) {
		Stat stat = new Stat();
		stat.setCountHuman(statDto.getCount_human_dna());
		stat.setCountMutant(statDto.getCount_mutant_dna());
		stat.setRatio(statDto.getRatio());
		return stat;
	}
	
	/**
	 * @param stat
	 * @return
	 */
	public static StatDTO toDto(Stat stat) {
		StatDTO statDTO = new StatDTO();
		statDTO.setCount_human_dna(stat.getCountHuman());
		statDTO.setCount_mutant_dna(stat.getCountMutant());
		statDTO.setRatio(stat.getRatio());
		return statDTO;
	}
	
}
