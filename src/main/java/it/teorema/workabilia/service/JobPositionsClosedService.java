package it.teorema.workabilia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.FindAllJobPositions;
import it.teorema.workabilia.mapper.JobView;
import it.teorema.workabilia.model.ClosedJobPositions;



public interface JobPositionsClosedService extends JpaRepository <ClosedJobPositions, Integer> {
	
	
	@Query("SELECT jp.task as task, jp.name as name, jp.description as description, jp.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "(CASE "
			+ "WHEN u.idRole = 1 THEN concat(ad.firstName, ' ', ad.lastName) "
			+ "WHEN u.idRole = 3 THEN az.denomination "
			+ "END) AS creatorName, p.name as province "
			+ "FROM ClosedJobPositions jp, StatesPositions sp, Compartments c, Sectors s, "
			+ "User u, Admin ad, Companies az, Province p "
			+ "WHERE jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id and sp.name = 'Chiusa' "
			+ "and jp.idCreatorUser = u.id and jp.idProvince = p.id and "
			+ "((u.idEntity = ad.id and u.idRole = 1) or (u.idRole = 3 and u.idEntity = az.id))"
			+ "GROUP BY jp.id "
			+ "ORDER BY jp.lastModificationDate desc")
	List<FindAllJobPositions> findAllJobPositionsHystoricAdmin();
	
	
	@Query("select jpc.task as task, jpc.name as name, jpc.description as description, jpc.id as idJob, "
			+ "sp.name as statePositionName, c.name as compartmentName, s.name as sectorName, "
			+ "p.name as province "
			+ "from ClosedJobPositions jpc, StatesPositions sp, Compartments c, Sectors s, "
			+ "Companies az, Province p "
			+ "where jpc.idState = sp.id and c.id = jpc.idCompartment and c.idSector = s.id "
			+ "and sp.name = 'Chiusa' and jpc.idCreatorUser = :idEntity and az.id = :idCompany "
			+ "and jpc.idProvince = p.id  "
			+ "order by jpc.lastModificationDate desc")
	List<FindAllJobPositions> getAllJobPositionsHystoricCompany(Integer idEntity, Integer idCompany);
	
	
	@Query("SELECT id "
			+ "FROM ClosedJobPositions "
			+ "WHERE lastModificationDate = :lastModificationDate")
	Integer getLast(LocalDateTime lastModificationDate);
	
	@Query("select jp.code as code, jp.name as jobName, jp.task as task, jp.description as description, "  
			+ "jp.activity as activity, c.id as idCompartment, c.name as compartment, "
			+ "s.id as idSector, s.name as sector, p.name as province " 
			+ "from ClosedJobPositions jp, StatesPositions sp, Compartments c, Sectors s, Province p "                    
			+ "where jp.idState = sp.id and c.id = jp.idCompartment and c.idSector = s.id "
			+ "and jp.id = :idJob and p.id = jp.idProvince")			 
	JobView findJobPositionHistory(int idJob);
	
	
	 
	
	
	


}


