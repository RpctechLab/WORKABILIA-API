package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.CandidateDetails;
import jakarta.transaction.Transactional;

public interface CandidateDetailsService extends JpaRepository <CandidateDetails, Integer> {

	
	@Query("from CandidateDetails "
			+ "where idCandidate = :idCandidate "
			+ "group by idCandidate")
	CandidateDetailsService existsByIdCandidate(int idCandidate);
	
	
	@Transactional
	@Modifying
	@Query("delete from CandidateDetails "
			+ "where idCandidate = :idCandidate")
	void deleteAllByIdCandidate(int idCandidate);

	@Query("from CandidateDetails "
			+ "where idCandidate = :idCandidate")
	List<CandidateDetails> findAllByIdCandidate(int idCandidate);
}