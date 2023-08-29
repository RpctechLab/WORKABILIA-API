package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Skills3;

public interface Skills3Service extends JpaRepository <Skills3, Integer> {

	@Query("select id "
			+ "from Skills3 "
			+ "where code = :codice3 and name = :nome")
	Integer findByCode(String codice3, String nome);
}