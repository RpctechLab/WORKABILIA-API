package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Guardians;
import jakarta.transaction.Transactional;

public interface GuardiansService extends JpaRepository <Guardians, Integer> {

	@Query("from Guardians "
			+ "where idCandidate = :idCandidate "
			+ "group by id")   //idCandidate
	Guardians existsByIdCandidate(int idCandidate);

	@Transactional
	@Modifying
	@Query("update Guardians "
			+ "set firstName = :#{#guardians.firstName}, lastName = :#{#guardians.lastName}, "
			+ "telephone = :#{#guardians.telephone} "
			+ "where idCandidate = :#{#guardians.idCandidate}")
	void updateGuardians(Guardians guardians);

	@Query("from Guardians "
			+ "where idCandidate = :idCandidate")
	Guardians findByIdCandidate(int idCandidate);
	
//	#{#guardians.idCandidate}
}