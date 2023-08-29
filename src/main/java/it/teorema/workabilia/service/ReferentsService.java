package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Referents;
import jakarta.transaction.Transactional;

public interface ReferentsService extends JpaRepository <Referents, Integer> {

	@Transactional
	@Modifying
	@Query("update Referents "
			+ "set firstName = :#{#referents.firstName}, "
			+ "lastName = :#{#referents.lastName}, "
			+ "phone = :#{#referents.phone}, "
			+ "email = :#{#referents.email} "
			+ "where idCompany = :idCompany")
	void updateCompany(Referents referents, Integer idCompany);

	@Query("from Referents where idCompany = :idCompany")
	Referents findByIdCompany(Integer idCompany);
}