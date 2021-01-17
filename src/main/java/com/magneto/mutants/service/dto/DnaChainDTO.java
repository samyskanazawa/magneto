package com.magneto.mutants.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samys
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaChainDTO {
	
	private List<String> dna;

}
