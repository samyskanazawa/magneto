package com.magneto.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magneto.mutants.repository.data.Stat;

/**
 * @author samys
 *
 */
public interface StatRepository extends JpaRepository<Stat, Long> {
}
