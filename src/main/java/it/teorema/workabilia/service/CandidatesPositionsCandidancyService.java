package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.CandidacyListAdmin;
import it.teorema.workabilia.mapper.CandidatesListAdmin;
import it.teorema.workabilia.mapper.CandidatiesListCompany;
import it.teorema.workabilia.model.CandidatesPositionsCandidancy;
import jakarta.transaction.Transactional;

public interface CandidatesPositionsCandidancyService extends JpaRepository <CandidatesPositionsCandidancy, Integer> {

	@Transactional
	@Modifying
	@Query("delete from CandidatesPositionsCandidancy "
			+ "where idPositioning = :idPosition and idCandidate = :idCandidate")
	void deleteByIds(int idCandidate, Integer idPosition);
	
	@Query("from CandidatesPositionsCandidancy "
			+ "where idPositioning = :idPosition and idCandidate = :idCandidate")
	CandidatesPositionsCandidancy  findByIds(int idCandidate, int idPosition);
	
	@Query("SELECT c.id as id, c.firstName as firstName, c.lastName as lastName, "
			+ "COUNT(cpc.idCandidate) as candidacy, c.telephone as telephone, c.email as email, "
			+ "u.state as state "
			+ "FROM Candidates c "
			+ "LEFT JOIN CandidatesPositionsCandidancy cpc ON cpc.idCandidate = c.id "
			+ "JOIN User u ON u.idEntity = c.id "
			+ "where u.idRole = 2 "
			+ "group by c.id, u.state")
	List<CandidatesListAdmin> findNCandidacy();
	
	@Query("select ca.id as id, ca.firstName as firstName, ca.lastName as lastName, "
			+ "pl.name as jobName, cpc.candidacyDate as candidacyDate, "
			+ "		round(((count(cs.skillCode) * 100)/ "
			+ "			(select count(ps.idPositions) "
			+ "				from PositionsSkills ps "
			+ "					where ps.idPositions = :idJob)), 0) as percentage "
			+ "from CandidatesSkills cs, Candidates ca, JobPositions pl, "
			+ "CandidatesPositionsCandidancy cpc "
			+ "where cs.idCandidate = :idCandidate and cs.idCandidate = ca.id and "
			+ "pl.id = cpc.idPositioning and pl.id = :idJob and cpc.idCandidate = ca.id and pl.idCreatorUser = :idUser "
			+ "and cs.skillCode "
			+ "		in (select ps.skillCode "
			+ "			from PositionsSkills ps "
			+ "				where ps.idPositions = :idJob) "
			+ "group by candidacyDate "
			+ "order by cpc.candidacyDate")   //da verificare
	CandidatiesListCompany getCandidatiesListCompany(Integer idCandidate, Integer idJob,Integer idUser);
	
	@Query("select ca.id as id, ca.firstName as firstName, ca.lastName as lastName, "
			+ "pl.name as jobName, cpc.candidacyDate as candidacyDate, "
			+ "		round(((count(cs.skillCode) * 100)/ "
			+ "			(select count(ps.idPositions) "
			+ "				from PositionsSkills ps "
			+ "					where ps.idPositions = :idJob)), 0) as percentage "
			+ "from CandidatesSkills cs, Candidates ca, JobPositions pl, "
			+ "CandidatesPositionsCandidancy cpc "			
			+ "where cs.idCandidate = :idCandidate and cs.idCandidate = ca.id "
			+ "and pl.id = cpc.idPositioning and pl.id = :idJob and cpc.idCandidate = ca.id "
			+ "and cs.skillCode "
			+ "		in (select ps.skillCode "
			+ "			from PositionsSkills ps "
			+ "				where ps.idPositions = :idJob) "
			+ "group by candidacyDate "
			+ "order by cpc.candidacyDate")
	List<CandidacyListAdmin> getCandidacyList(Integer idJob, Integer idCandidate);
	
	@Query("SELECT idCandidate "
			+ "FROM CandidatesPositionsCandidancy "
			+ "WHERE idPositioning = :idJob")
	List<Integer> getIdCandidateByIdJob (Integer idJob);
	
	@Transactional
	@Modifying
	@Query("delete from CandidatesPositionsCandidancy "
			+ "where idPositioning = :idJob")
	void deleteAllByIdJob(Integer idJob);
	
}






















