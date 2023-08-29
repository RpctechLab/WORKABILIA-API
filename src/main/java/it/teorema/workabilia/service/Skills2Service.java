package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Skills2;

public interface Skills2Service extends JpaRepository <Skills2, Integer> {

	@Query("select id "
			+ "from Skills2 "
			+ "where code = :codice2 and name = :nome")
	Integer findByCode(String codice2, String nome);
}