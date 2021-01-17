package com.magneto.mutants.service.dto;


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
public class StatDTO {
	
	private int count_mutant_dna;
	
	private int count_human_dna;
	
	private double ratio;
	
}
