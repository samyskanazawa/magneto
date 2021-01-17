package com.magneto.mutants.service;

import com.magneto.mutants.service.dto.DnaChainDTO;
import com.magneto.mutants.service.dto.StatDTO;

/**
 * @author samys
 *
 */
public interface DnaChainService {
	
	/**
	 * Determina si un ADN pertenece o no a un mutante
	 * @param chain
	 * @return
	 */
	boolean isMutant(DnaChainDTO dnaChain) throws IllegalArgumentException;

	/**
	 * Retorna proporcion de mutantes con respecto a los humanos
	 * @return
	 */
	StatDTO getStats();
	
}
