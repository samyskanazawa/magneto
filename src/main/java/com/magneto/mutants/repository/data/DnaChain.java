package com.magneto.mutants.repository.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

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
@Entity
@Table(name="DNAS")
public class DnaChain implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dna_id;
	
	@NotNull
	@Column(name="DNA")
	private String dna;
	
	@NotNull
	@Column(name="IS_MUTANT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isMutant;
	
	@NotNull
	@Column(name="LENGTH")
	private Integer length;
	
	@Column(name="RECORDING")
	private Date recording;
	
	@NotNull
	@Column(name="REMOVED")
	private Integer removed;

}
