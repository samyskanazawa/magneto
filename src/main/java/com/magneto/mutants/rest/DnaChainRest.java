package com.magneto.mutants.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magneto.mutants.service.DnaChainService;
import com.magneto.mutants.service.dto.DnaChainDTO;
import com.magneto.mutants.service.dto.StatDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @author samys
 *
 */
@RestController
@RequestMapping("/api")
public class DnaChainRest {

	@Inject
	private DnaChainService dnaChainService;
	
	/**
	 * @param dnaChainDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = "/mutant", produces={MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Evalua una cadena de ADN", notes = "Si es o no un mutante", tags = {"mutant"})
    public ResponseEntity<?> isMutant(@RequestBody DnaChainDTO dna) throws IllegalArgumentException {
		Boolean confirm = dnaChainService.isMutant(dna);
		return confirm ? 	new ResponseEntity<>(confirm, HttpStatus.OK) : 
							new ResponseEntity<>(confirm, HttpStatus.FORBIDDEN); 
    }
	
	/** Retorna la proporcion de ADNs verificados
	 * @return
	 */
	@GetMapping(path = "/stats", produces={MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Devuelve las estadisticas", notes = "Proporcion de ADNs verificados", tags = {"stats"})
    public ResponseEntity<?> getStats() {
		StatDTO statDTO = dnaChainService.getStats();
		return statDTO == null ?	new ResponseEntity<>("No existen estadisticas previas", HttpStatus.FORBIDDEN) :
									new ResponseEntity<>(statDTO, HttpStatus.OK);
    }
	
}
