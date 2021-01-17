package com.magneto.mutants.repository.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name="stats")
public class Stat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long stat_id;
	
	@NotNull
	@Column(name="mutant_count")
	private int countMutant;
	
	@NotNull
	@Column(name="human_count")
	private int countHuman;
	
	@NotNull
	@Column(name="ratio")
	private double ratio;
}
