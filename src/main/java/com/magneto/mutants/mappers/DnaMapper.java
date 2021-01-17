package com.magneto.mutants.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.magneto.mutants.repository.data.DnaChain;
import com.magneto.mutants.service.dto.DnaChainDTO;

/**
 * @author samys
 *
 */
public class DnaMapper {

	/**
	 * @param dnaChainDTO
	 * @param isMutant
	 * @return
	 */
	public static DnaChain toEntity(DnaChainDTO dnaChainDTO, boolean isMutant) {
		DnaChain dna = new DnaChain();
		dna.setIsMutant(isMutant);
		dna.setLength(dnaChainDTO.getDna().size());
		dna.setRecording(new Date());
		dna.setRemoved(0);
		StringBuilder chain = new StringBuilder();
		for(String dnaChain : dnaChainDTO.getDna()) {
			chain.append(dnaChain);
		}
		dna.setDna(chain.toString());
		return dna;
	}
	
	/**
	 * @param dnaChain
	 * @return
	 */
	public static DnaChainDTO toDto(DnaChain dnaChain) {
		DnaChainDTO dnaChainDTO = new DnaChainDTO();
		List<String> dnaChains = new ArrayList<String>();
		int i = 0;
		String dna = dnaChain.getDna();
		while(i < dna.length()) {
			dnaChains.add(dna.substring(i, Math.min(i + dnaChain.getLength(), dna.length())));
			i += dnaChain.getLength();
		}
		dnaChainDTO.setDna(dnaChains);
		return dnaChainDTO;
	}
	
}
