package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.CandidatesSkills;
import jakarta.transaction.Transactional;

public interface CandidatesSkillsService extends JpaRepository <CandidatesSkills, Integer>{
	
	@Modifying
	@Transactional
	@Query("delete from CandidatesSkills "
			+ "where idCandidate = :idCandidate")
	void deleteAllByIdCandidate(int idCandidate);
	
	@Query("select skillCode "
			+ "from CandidatesSkills "
			+ "where idCandidate = :idCandidate")
	List<String> getAllByIdCandidate(int idCandidate);
}