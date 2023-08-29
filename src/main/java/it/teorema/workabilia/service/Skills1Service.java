package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Skills1;

public interface Skills1Service extends JpaRepository <Skills1, Integer> {

	@Query("select id "
			+ "from Skills1 "
			+ "where code = :codice1")
	Integer findByCode(String codice1);
}