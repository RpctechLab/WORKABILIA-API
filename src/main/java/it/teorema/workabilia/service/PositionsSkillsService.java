package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.PositionsSkills;
import jakarta.transaction.Transactional;

public interface PositionsSkillsService extends JpaRepository <PositionsSkills, Integer> {

	@Query("select skillCode "
			+ "from PositionsSkills "
			+ "where idPositions = :idPosition")
	List<String> allSkillsByJobPositions(int idPosition);

	@Query("select id "
			+ "from PositionsSkills "
			+ "group by id")
	List<Integer> getIdPositionByLastSkill();
	
	@Query("select skillCode "
			+ "from PositionsSkills "
			+ "where LENGTH(skillCode) = 4 and idPositions = :id")
	List<String> getSkills3ByIdPosition(Integer id);
	
	@Query("SELECT idPositions "
			+ "FROM PositionsSkills "
			+ "WHERE idPositions = :idJob "
			+ "GROUP BY idPositions")
	Integer existJobSkills(Integer idJob);
	
	@Transactional
	@Modifying
	@Query("delete from PositionsSkills "
			+ "where idPositions = :id")
	void deleteAllByIdPosition(int id);
	
	@Transactional
	@Modifying
	@Query("update PositionsSkills "
			+ "set idPositions = :idPositionClosed "
			+ "where idPositions = :idPosition")
	void skillOpenToClose(Integer idPosition, Integer idPositionClosed);
	
	/*@Query("select skillCode "
			+ "from PositionsSkills "
			+ "where LENGTH(skillCode) = 4 and idPositions in (:ids)")
	List<String> getSkills3ByIdPositions(List<Integer> ids);*/
}