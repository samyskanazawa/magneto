package com.magneto.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magneto.mutants.repository.data.DnaChain;

/**
 * @author samys
 *
 */
public interface DnaRepository extends JpaRepository<DnaChain, Long> {

	DnaChain findDnaChainByDna(String dna);

}
