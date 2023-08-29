package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Candidates;
import jakarta.transaction.Transactional;

public interface CandidatesService extends JpaRepository<Candidates, Integer> {

	@Query("from Candidates "
			+ "where email = :email")
	Candidates existsByEmail(String email);
	
	@Query("from Candidates "
			+ "where email = :email "
			+ "and id != :idCandidate")
	Candidates existsByEmailCandidates(String email, Integer idCandidate);
	
	@Query("from Candidates "
			+ "where email = :email ")
	Candidates existsByEmailCandidates(String email);
	
	@Query("SELECT c.id "
			+ "FROM Candidates c")
	List<Integer> getAllIdCandidates ();

	@Query("select id "
			+ "from Candidates " 
			+ "where email = :email")
	int findIdByEmail(String email);

	@Modifying
	@Transactional
	@Query("update Candidates "
			+ "set firstName = :#{#candidates.firstName}, lastName = :#{#candidates.lastName}, "
			+ "gender = :#{#candidates.gender}, birthDate = :#{#candidates.birthDate}, "
			+ "birthPlace = :#{#candidates.birthPlace}, fiscalCode= :#{#candidates.fiscalCode}, "
			+ "citizenship = :#{#candidates.citizenship}, "
			+ "residence = :#{#candidates.residence}, domicile = :#{#candidates.domicile}, "
			+ "telephone = :#{#candidates.telephone}, email = :#{#candidates.email} "
			+ "where id = :idCandidate")
	void updateCandidate(Candidates candidates, int idCandidate);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO ca_candidati (nome, cognome, email) " 
			+ "VALUES (:firstName, :lastName, :email)", nativeQuery = true)
	void insertCandidate(String firstName, String lastName, String email);
}







