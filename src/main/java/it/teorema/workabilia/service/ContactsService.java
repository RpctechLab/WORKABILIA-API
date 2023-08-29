package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Contacts;
import jakarta.transaction.Transactional;

public interface ContactsService extends JpaRepository <Contacts, Integer> {

	
	@Query("from Contacts "
			+ "where idCandidate = :idCandidate "
			+ "group by idCandidate")
	Contacts existByIdCandidate(int idCandidate);

	
	@Modifying
	@Transactional
	@Query("delete from Contacts "
			+ "where idCandidate = :idCandidate")
	void deleteAllByIdCandidate(int idCandidate);


	@Query("from Contacts "
			+ "where idCandidate = :idCandidate")
	List<Contacts> findAllByIdCandidate(int idCandidate);
}