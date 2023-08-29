package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Province;

public interface ProvinceService extends JpaRepository <Province, Integer> {
	
	@Query("SELECT id "
			+ "FROM Province "
			+ "WHERE name = :name")
	Integer getIdByName(String name);
}
