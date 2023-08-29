package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.CandidatesPositionsCandidancy;
import it.teorema.workabilia.model.CandidatesPositionsCandidancyClosed;
import jakarta.transaction.Transactional;

public interface CandidatesPositionsCandidancyClosedService extends JpaRepository <CandidatesPositionsCandidancyClosed, Integer> {
	
	@Transactional
	@Modifying
	@Query("UPDATE CandidatesPositionsCandidancyClosed cpcc "
			+ "SET idPositioning = :idPositionClosed "
			+ "WHERE idPositioning = :idOldJob")
	void updateIdJob (Integer idPositionClosed, Integer idOldJob);
	
	@Query("FROM CandidatesPositionsCandidancy cpc "
			+ "WHERE cpc.idPositioning = :idJob")
	List<CandidatesPositionsCandidancy> findByIdJob(Integer idJob);
}