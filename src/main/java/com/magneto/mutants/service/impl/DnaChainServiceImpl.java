package com.magneto.mutants.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magneto.mutants.Constants;
import com.magneto.mutants.mappers.DnaMapper;
import com.magneto.mutants.mappers.StatMapper;
import com.magneto.mutants.repository.DnaRepository;
import com.magneto.mutants.repository.StatRepository;
import com.magneto.mutants.repository.data.DnaChain;
import com.magneto.mutants.repository.data.Stat;
import com.magneto.mutants.service.DnaChainService;
import com.magneto.mutants.service.dto.DnaChainDTO;
import com.magneto.mutants.service.dto.StatDTO;

/**
 * @author samys
 *
 */
@Service
public class DnaChainServiceImpl implements DnaChainService {

	@Autowired
	private DnaRepository dnaRepository;
	@Autowired
	private StatRepository statRepository;

	private Integer chainCount;
	private Integer a;
	private Integer t;
	private Integer c;
	private Integer g;
	
	@Override
	public boolean isMutant(DnaChainDTO dnaChainDto) throws IllegalArgumentException {
		evaluateFormat(dnaChainDto.getDna());
		this.chainCount = 0;
//		Convierto la lista en una unica cadena para ver si fue verificado o no
		StringBuilder chain = new StringBuilder();
		for(String dna : dnaChainDto.getDna()) {
			chain.append(dna);
		}
//		Verifico si ya fue grabada anteriormente
		DnaChain dna = dnaRepository.findDnaChainByDna(chain.toString());
		if(dna != null) {
			return dna.getIsMutant();
		}	
//		Comienza la verificacion del ADN
		boolean mutant = horizontalTravel(dnaChainDto.getDna());
		if(!mutant) {
			mutant = verticalTravel(dnaChainDto.getDna());
		}
		if(!mutant) {
			mutant = obliqueTravel(dnaChainDto.getDna());
		}
//		Mappeo el dto de entrada a una entidad
		dna = DnaMapper.toEntity(dnaChainDto, mutant, chain.toString());
//		Guardo el ADN
		dnaRepository.save(dna);
//		Actualizo los stats
		updateStats(mutant);
		
		return mutant;
	}
	
	@Override
	public StatDTO getStats() {
		List<Stat> stats = statRepository.findAll();
		StatDTO dto = null;
		if(!stats.isEmpty()) {
			dto = StatMapper.toDto(stats.get(0));
		}
		return dto;
	}

	/**Actualiza los stats
	 * @param mutant
	 */
	private void updateStats(boolean mutant) {
		List<Stat> stats = statRepository.findAll();
		Stat stat = null;
//		Existen estadisticas previas
		if(!stats.isEmpty()) {
			stat = stats.get(0);
		} else {
			stat = new Stat();
			stat.setCountMutant(0);
			stat.setCountHuman(0);
		}
		if(mutant) {
			stat.setCountMutant(stat.getCountMutant()+1);
		} else {
			stat.setCountHuman(stat.getCountHuman()+1);
		}
//		Validacion para evitar un ArithmeticException
		if(stat.getCountHuman()== 0) {
			stat.setRatio(stat.getCountMutant());
		} else {
			stat.setRatio((double)stat.getCountMutant()/stat.getCountHuman());
		}
		statRepository.save(stat);
	}
	
//	VALIDADORES Y TOTALIZADORES	
	
	/** Evalua si alguno de las cadenas tiene un valor menor a N o al valor minimo de N
	 * @param dnaChain
	 * @throws IllegalArgumentException
	 */
	private void evaluateFormat(List<String>dnaChain) throws IllegalArgumentException {
		if(dnaChain.size() < Constants.MIN_VALUE_CHAIN ) {
			throw new IllegalArgumentException(Constants.INCOMP_DNA_CHAIN);
		}
		for(String chain : dnaChain) {
			if(chain.length()!= dnaChain.size()) {
				throw new IllegalArgumentException(Constants.INCOMP_DNA_CHAIN);
			}
		}
	}
	
	
	/**
	 * Validador de genes
	 * @param value
	 * @param count
	 * @param gene
	 * @return Totaliza sobre la variable que recibe si coninciden los genes comparados y 
	 * o la reinicia en caso contrario
	 */
	private int validator(char value, int count, char gene) {
		if(value == gene) {
			count++;
		} else {
			count=0;
		}
		return count;
	}
	
	/**
	 * Se inicializa el totalizador de genes
	 */
	private void initGenes() {
		a=0; t=0; c=0; g=0;
	}
	
//	BUSQUEDA HORIZONTAL
	/**
	 * @param chain
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean hVtravel(String chain) {
		initGenes();
		for(int i= 0; i< chain.length() ; i++) {
			a= validator(chain.charAt(i), a, Constants.GENE_A);
			t= validator(chain.charAt(i), t, Constants.GENE_T);
			c= validator(chain.charAt(i), c, Constants.GENE_C);
			g= validator(chain.charAt(i), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			if(chainCount >= Constants.NUMBER_MATCHES) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busqueda horizontal sobre cada cadena recibida
	 * @param chains
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean horizontalTravel(List<String> chains) {
		for(String chain: chains) {
			Boolean isMutant = hVtravel(chain);
			if(isMutant) return true;
		}
		return false;
	}
	
//	BUSQUEDA VERTICAL
	/**
	 * @param chains
	 * @param index
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean vVtravel(List<String> chains, int index) {
		initGenes();
		for(String chain: chains) {
			a= validator(chain.charAt(index), a, Constants.GENE_A);
			t= validator(chain.charAt(index), t, Constants.GENE_T);
			c= validator(chain.charAt(index), c, Constants.GENE_C);
			g= validator(chain.charAt(index), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			if(chainCount >= Constants.NUMBER_MATCHES) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca en forma vertical
	 * @param dnaChain
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean verticalTravel(List<String>dnaChain) {
		for(int i=0; i< dnaChain.size(); i++) {
			Boolean isMutant = vVtravel(dnaChain, i);
			if(isMutant) return true;
		}
		return false;
	}
	
//	BUSQUEDA OBLICUA
	/**
	 * Busca en forma "oblicua" desde la diagonal izquierda a la derecha
	 * @param dnaChain
	 * @param i
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean oVTravelUpToRight(List<String> dnaChain, int i) {
		initGenes();
		int index = i;
		for(int j = 0; j < dnaChain.size()-i; j++) {
			a= validator(dnaChain.get(j).charAt(index), a, Constants.GENE_A);
			t= validator(dnaChain.get(j).charAt(index), t, Constants.GENE_T);
			c= validator(dnaChain.get(j).charAt(index), c, Constants.GENE_C);
			g= validator(dnaChain.get(j).charAt(index), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			if(chainCount >= Constants.NUMBER_MATCHES) {
				return true;
			}
			index++;
		}
		return false;
	}
	
	/**
	 * Busca en forma "oblicua" desde la diagonal izquierda hacia la derecha
	 * @param dnaChain
	 * @param i
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean oVTravelDownToRight(List<String> dnaChain, int i) {
		initGenes();
		int index = i;
		for(int k= 0; k < dnaChain.size()-i; k++) {
			a= validator(dnaChain.get(index).charAt(k), a, Constants.GENE_A);
			t= validator(dnaChain.get(index).charAt(k), t, Constants.GENE_T);
			c= validator(dnaChain.get(index).charAt(k), c, Constants.GENE_C);
			g= validator(dnaChain.get(index).charAt(k), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			index++;
		}
		if(chainCount >= Constants.NUMBER_MATCHES) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param dnaChain
	 * @param numOblique
	 * @return
	 */
	private boolean obliqueTravelLeftToRight(List<String> dnaChain, int numOblique) {
//		Recorrido desde la diagonal hacia arriba
		for(int i = 0; i <= numOblique; i++ ) {
			boolean  isMutant = oVTravelUpToRight(dnaChain, i);
			if(isMutant) return true;
		}
//		Recorrido por debajo de la diagonal
		for(int j = 1; j <= numOblique; j++ ) {
			boolean  isMutant = oVTravelDownToRight(dnaChain, j);
			if(isMutant) return true;
		}
		return false;
	}
	
	/**
	 * Busca en forma oblicua de derecha a izquierda
	 * @param dnaChain
	 * @param i
	 * @return True si encontro dos cadenas de caracteres
	 */
	private boolean oVTravelDownToLeft(List<String> dnaChain, int i) {
		initGenes();
		int index = i;
		for(int j = dnaChain.size()-1; j >= i  ; j--) {
			a= validator(dnaChain.get(index).charAt(j), a, Constants.GENE_A);
			t= validator(dnaChain.get(index).charAt(j), t, Constants.GENE_T);
			c= validator(dnaChain.get(index).charAt(j), c, Constants.GENE_C);
			g= validator(dnaChain.get(index).charAt(j), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			if(chainCount >= Constants.NUMBER_MATCHES) {
				return true;
			}
			index++;
		}
		return false;
	}
	
	/**
	 * @param dnaChain
	 * @param i
	 * @return
	 */
	private boolean oVTravelUpToLeft(List<String> dnaChain, int i) {
		initGenes();
		int index = 0;
		for(int j = dnaChain.size()-i-2; j >=0 ; j--) {
			a= validator(dnaChain.get(index).charAt(j), a, Constants.GENE_A);
			t= validator(dnaChain.get(index).charAt(j), t, Constants.GENE_T);
			c= validator(dnaChain.get(index).charAt(j), c, Constants.GENE_C);
			g= validator(dnaChain.get(index).charAt(j), g, Constants.GENE_G);
			if(a==Constants.MIN_VALUE_CHAIN || t==Constants.MIN_VALUE_CHAIN || c==Constants.MIN_VALUE_CHAIN || g==Constants.MIN_VALUE_CHAIN) {
				this.chainCount++;
			}
			if(chainCount >= Constants.NUMBER_MATCHES) {
				return true;
			}
			index++;
		}
		return false;
	}
	
	/**
	 * @param dnaChain
	 * @param numOblique
	 * @return
	 */
	private boolean obliqueTravelRightToLeft(List<String> dnaChain, int numOblique) {
//		Recorrido desde la diagonal hacia arriba
		for(int i = 0; i <= numOblique; i++ ) {
			boolean  isMutant = oVTravelDownToLeft(dnaChain, i);
			if(isMutant) return true;
		}
//		Recorrido por debajo de la diagonal
		for(int i = 0; i <= numOblique; i++ ) {
			boolean  isMutant = oVTravelUpToLeft(dnaChain, i);
			if(isMutant) return true;
		}
		return false;
	}

	/**
	 * Recorre la matriz en forma oblicua
	 * @param dnaChain
	 * @return True si encuentra 2 cadenas de genes en caso contrario False
	 */
	private boolean obliqueTravel(List<String> dnaChain) {
//		N*2-7 Cantidad de lineas oblicuas > a 4 a partir de la diagonal principal
		int numOblique = (dnaChain.size()*Constants.NUMBER_CHAINS - Constants.LOCKED_ITEMS)/2;
		if(obliqueTravelLeftToRight(dnaChain, numOblique)) {
			return true;
		}
		return obliqueTravelRightToLeft(dnaChain, numOblique);
	}
	
}
