package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Sectors;

public interface SectorsService extends JpaRepository <Sectors, Integer> {

	@Query("select id "
			+ "from Sectors "
			+ "where name = :sector")
	Integer findByName(String sector);
}