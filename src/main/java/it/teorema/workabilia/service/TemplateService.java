package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Template;
import jakarta.transaction.Transactional;

public interface TemplateService extends JpaRepository <Template, Integer> {

	@Query("select file "
			+ "from Template "
			+ "where name = :name")
	String findByName(String name);
	
	@Modifying
	@Transactional
	@Query("update Template "
			+ "set file =:file "
			+ "where name = 'attivita'")
	void updateAttivita(String file);
	
	@Modifying
	@Transactional
	@Query("update Template "
			+ "set file =:file "
			+ "where name = 'abilita'")
	void updateAbilita(String file);
}
