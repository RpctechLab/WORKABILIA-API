package it.teorema.workabilia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.FindAllJobPositions;
import it.teorema.workabilia.mapper.JobPositionEdit;
import it.teorema.workabilia.mapper.JobView;
import it.teorema.workabilia.mapper.SummarySkills;
import it.teorema.workabilia.model.CandidatesPositionsPreference;
import it.teorema.workabilia.model.JobPositions;
import jakarta.transaction.Transactional;

public interface JobPositionsService extends JpaRepository <JobPositions, Integer> {

	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name != 'Chiusa'"
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositions();
	
	@Query("SELECT jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "(CASE "
			+ "WHEN u.idRole = 1 THEN concat(ad.firstName, ' ', ad.lastName) "
			+ "WHEN u.idRole = 3 THEN az.denomination "
			+ "END) AS creatorName, p.name as province "
			+ "FROM JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p "
			+ "WHERE jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id and sp.name != 'Chiusa' "
			+ "and jp.idCreatorUser = u.id and jp.idProvince = p.id and "
			+ "((u.idEntity = ad.id and u.idRole = 1) or (u.idRole = 3 and u.idEntity = az.id)) "
			+ "GROUP BY jp.id, creatorName "
			+ "ORDER BY jp.lastModificationDate desc")
	List<FindAllJobPositions> findJobPositionAdmin();
	
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "az.denomination AS creatorName, p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Companies az, Province p "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name != 'Chiusa' and jp.idCreatorUser = :idEntity and u.id = :idEntity "
			+ "and az.id = u.idEntity and jp.idProvince = p.id and u.idRole = 3 "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositionsCompany(int idEntity);
	
	/*
	//Reali
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "az.denomination AS creatorName, r.name AS creatorRole, p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p, Roles r "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name = 'Pubblicata' and r.id = u.idRole "
			+ "and jp.id not in (select idPositioning from CandidatesPositionsCandidancy where idCandidate = :idCandidate) "
			+ "and u.idRole = 3 and u.idEntity = az.id and jp.idProvince = p.id and jp.idCreatorUser = u.id "
			+ "group by jp.id, creatorName "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllRealJobPositionsOpen(Integer idCandidate);*/
	
	@Query(value = "SELECT jp.mansione as task, jp.denominazione as name, jp.descrizione as description, jp.id as idJob, "
			+ "sp.nome as statePositionName, c.nome as compartmentName, s.nome as sectorName, "
			+ "concat(ad.nome, ' ', ad.cognome) AS creatorName, r.nome AS creatorRole, p.nome as province "
			+ "FROM pl_posizioni_lavorative jp "
			+ "LEFT JOIN ty_stati_posizioni sp on jp.id_stato = sp.id "
			+ "LEFT JOIN ty_comparti c on c.id = jp.id_comparto "
			+ "LEFT JOIN ty_settori s on c.id_settore = s.id "
			+ "LEFT JOIN ap_user u on jp.id_user_creatore = u.id "
			+ "JOIN ad_admin ad "
			+ "LEFT JOIN az_aziende az on u.id_entity = az.id "
			+ "LEFT JOIN ty_province p on jp.id_provincia = p.id "
			+ "LEFT JOIN ty_ruoli r on r.id = u.id_ruolo "
			+ "WHERE sp.nome = 'Pubblicata' "
			+ "and jp.id not in (select id_posizione from as_candidati_posizioni_candidatura where id_candidato = :idCandidate) "
			+ "and u.id_ruolo = 3 "
			+ "group by jp.id, creatorName "
			+ "order by jp.data_ultima_modifica desc", nativeQuery = true)
	List<FindAllJobPositions> findAllRealJobPositionsOpen(Integer idCandidate);
	
/*
	//Ideali
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "concat(ad.firstName, ' ', ad.lastName) AS creatorName, r.name AS creatorRole, p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p, Roles r "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name = 'Pubblicata' and r.id = u.idRole "
			+ "and jp.id not in (select idPositioning from CandidatesPositionsCandidancy where idCandidate = :idCandidate) "
			+ "and u.idRole = 1 and u.idEntity = az.id and jp.idProvince = p.id and jp.idCreatorUser = u.id "
			+ "group by jp.id, creatorName "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllIdealJobPositionsOpen(Integer idCandidate);*/
	
	@Query(value = "SELECT jp.mansione as task, jp.denominazione as name, jp.descrizione as description, jp.id as idJob, "
			+ "sp.nome as statePositionName, c.nome as compartmentName, s.nome as sectorName, "
			+ "concat(ad.nome, ' ', ad.cognome) AS creatorName, r.nome AS creatorRole, p.nome as province "
			+ "FROM pl_posizioni_lavorative jp "
			+ "LEFT JOIN ty_stati_posizioni sp on jp.id_stato = sp.id "
			+ "LEFT JOIN ty_comparti c on c.id = jp.id_comparto "
			+ "LEFT JOIN ty_settori s on c.id_settore = s.id "
			+ "LEFT JOIN ap_user u on jp.id_user_creatore = u.id "
			+ "JOIN ad_admin ad "
			+ "LEFT JOIN az_aziende az on u.id_entity = az.id "
			+ "LEFT JOIN ty_province p on jp.id_provincia = p.id "
			+ "LEFT JOIN ty_ruoli r on r.id = u.id_ruolo "
			+ "WHERE sp.nome = 'Pubblicata' "
			+ "and jp.id not in (select id_posizione from as_candidati_posizioni_candidatura where id_candidato = :idCandidate) "
			+ "and u.id_ruolo = 1 "
			+ "group by jp.id, creatorName "
			+ "order by jp.data_ultima_modifica desc", nativeQuery = true)
	List<FindAllJobPositions> findAllIdealJobPositionsOpen(Integer idCandidate);
	
	/*
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "(CASE "
			+ "WHEN u.idRole = 1 THEN concat(ad.firstName, ' ', ad.lastName) "
			+ "WHEN u.idRole = 3 THEN az.denomination "
			+ "END) AS creatorName, "
			+ "(CASE "
			+ "WHEN u.idRole = 1 THEN 'Admin' "
			+ "WHEN u.idRole = 3 THEN 'Company' "
			+ "END) AS creatorRole, "
			+ "p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name = 'Pubblicata' "
			+ "and jp.id not in (select idPositioning from CandidatesPositionsCandidancy where idCandidate = :idCandidate) "
			+ "and ((u.idEntity = ad.id and u.idRole = 1) or (u.idRole = 3 and u.idEntity = az.id)) "
			+ "and jp.idProvince = p.id and jp.idCreatorUser = u.id "
			+ "group by jp.id "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositionsOpen(Integer idCandidate);*/
		
	 
	
	
	// query admin
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "p.name as province, concat(ad.firstName, ' ', ad.lastName) AS creatorName, u.idRole AS creatorRole "		 
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and u.idRole = 1 "
			+ "and sp.name = 'Pubblicata' "
			+ "and jp.id not in (select idPositioning from CandidatesPositionsCandidancy where idCandidate = :idCandidate) "
			+ "and (u.idEntity = ad.id and u.idRole = 1) "
			+ "and jp.idProvince = p.id and jp.idCreatorUser = u.id "
			+ "group by jp.id, creatorName "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositionsOpenAdmin(Integer idCandidate);
	
	// query aziende
		@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
				+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
				+ "p.name as province,az.denomination AS creatorName, u.idRole  AS creatorRole "		 
				+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
				+ "User u, Admin ad, Companies az, Province p "
				+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
				+ "and u.idRole = 3 "
				+ "and sp.name = 'Pubblicata' "
				+ "and jp.id not in (select idPositioning from CandidatesPositionsCandidancy where idCandidate = :idCandidate) "
				+ "and (u.idRole = 3  and u.idEntity = az.id) "
				+ "and jp.idProvince = p.id and jp.idCreatorUser = u.id "
				+ "group by jp.id, creatorName "
				+ "order by jp.lastModificationDate desc")
		List<FindAllJobPositions> findAllJobPositionsOpenCompany(Integer idCandidate);
	
	
	
	
	
	
	@Query("select jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "(CASE "
			+ "WHEN u.idRole = 1 THEN concat(ad.firstName, ' ', ad.lastName) "
			+ "WHEN u.idRole = 3 THEN az.denomination "
			+ "END) AS creatorName, p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and sp.name = 'Pubblicata' and jp.id in (select idPositioning from CandidatesPositionsCandidancy) "
			+ "and (u.idRole = 3 and u.idEntity = az.id) "
			+ "and jp.idProvince = p.id "
			+ "group by jp.id, creatorName "
			+ "order by jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositionsOpenCandidancy();
	
	@Query("select jp.name as name, jp.code as code "
			+ "from JobPositions jp "
			+ "where jp.code = :code")
	SummarySkills getAllJobPositionsName(String code);
	
	@Query("select jp.code as code, jp.name as jobName, jp.task as task, jp.description as description, "
			+ "jp.activity as activity, c.id as idCompartment, c.name as compartment, u.idRole as creatorRole, "
			+ "s.id as idSector, s.name as sector, p.id as idProvince, p.name as province "
			+ "from JobPositions jp, StatesPositions sp, Compartments c, Sectors s, Province p,User u "
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and jp.id = :idJob and p.id = jp.idProvince and jp.idCreatorUser = u.id")
	JobView findJobPosition(int idJob);
	
	@Query("SELECT COUNT(idPositioning) "
			+ "			FROM CandidatesPositionsCandidancy "
			+ "				WHERE idPositioning = :idJob")
	Integer findCandidacy(Integer idJob);
	
	/*
	@Query("select round(((count(skillCode) * 100)/ "
			+ "(select count(idPositions) "
				+ "from PositionsSkills "
					+ "where idPositions = :#{#jp.idJob})), 0) as percentage "
			+ "from CandidatesSkills "
			+ "where idCandidate = :idCandidate and skillCode "
				+ "in (select skillCode "
					+ "from PositionsSkills "
						+ "where idPositions = :#{#jp.idJob})")
	Integer findAllMatch(int idCandidate, FindAllJobPositions jp);*/
	
	@Query(value = "select round(((count(codice_abilita) * 100)/ "
			+ "(select count(id_posizione) "
			+ "from as_posizioni_abilita "
			+ "where id_posizione = :#{#jp.idJob})), 0) as percentage "
			+ "from as_candidati_abilita "
			+ "where id_candidato = :idCandidate and codice_abilita "
			+ "in (select codice_abilita "
			+ "from as_posizioni_abilita "
			+ "where id_posizione = :#{#jp.idJob})", nativeQuery = true)
	Integer findAllMatch(int idCandidate, FindAllJobPositions jp);

	@Query("SELECT name "
			+ "FROM JobPositions jp "
			+ "WHERE id = :idJob")
	String findNameByJobId(Integer idJob);
	
	@Query("select MAX(id) "
			+ "from JobPositions ")
	Integer findLastJobPositionId();

	/*@Query("select id "
			+ "from JobPositions "
			+ "where code like %:code%")
	Integer findByCode(String code);*/
	
	@Query("select id "
			+ "from JobPositions "
			+ "where code = :code")
	Integer findByCode(String code);
	
	@Modifying
	@Transactional
	@Query("update JobPositions "
			+ "set idCompartment = :#{#jb.idCompartment}, code = :#{#jb.code}, task = :#{#jb.task}, "
			+ "name = :#{#jb.name}, description = :#{#jb.description}, activity = :#{#jb.activity}, "
			+ "idProvince = :#{#jb.IdProvince}, lastModificationDate = :lastModificationDate "
			+ "where id = :idJob")
	void updateJobPosition(int idJob, JobPositionEdit jb, LocalDateTime lastModificationDate);

	@Transactional
	@Modifying
	@Query("update JobPositions "
			+ "set idState = 2, lastModificationDate = :date "
			+ "where id = :idPosition")
	void updateStatusJobPosition(Integer idPosition, LocalDateTime date);

	
	@Transactional
	@Modifying
	@Query("update JobPositions "
			+ "set idState = :idState "
			+ "where id = :idPosition")
	void updateStateJobPosition(Integer idState, Integer idPosition);
	
	
	@Query("from CandidatesPositionsPreference "
			+ "where idCandidate = :idCandidate and idPosition = :idPosition")
	CandidatesPositionsPreference findFavourite(int idCandidate, int idPosition);

	@Transactional
	@Modifying
	@Query("update JobPositions "
			+ "set lastModificationDate = :data "
			+ "where id = :idPosition")
	void updateLastDate(LocalDateTime data, Integer idPosition);
	
	@Query("from JobPositions "
			+ "where code = :code "
			+ "group by code, id")
	JobPositions findCode(String code);

	@Query("from JobPositions "
			+ "where id = :idPosition ")			 
	JobPositions findbyId(Integer idPosition);
	 
}


















