package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.CandidatesPositionsPreference;
import jakarta.transaction.Transactional;

public interface CandidatesPositionsPreferenceService extends JpaRepository <CandidatesPositionsPreference, Integer> {
	
	@Transactional
	@Modifying
	@Query("delete from CandidatesPositionsPreference "
			+ "where idPosition = :idPosition and idCandidate = :idCandidate")
	void deleteFavouriteById(Integer idPosition, Integer idCandidate);
}