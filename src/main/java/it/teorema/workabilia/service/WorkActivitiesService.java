package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.MedicalDiagnosesIcd;
import it.teorema.workabilia.model.WorkActivities;
import jakarta.transaction.Transactional;

public interface WorkActivitiesService extends JpaRepository <WorkActivities, Integer>{
	@Query("from WorkActivities "
			+ "where idCandidate = :idCandidate "
			+ "group by idCandidate")
	MedicalDiagnosesIcd existByIdCandidate(int idCandidate);

	@Modifying
	@Transactional
	@Query("delete from WorkActivities "
			+ "where idCandidate = :idCandidate")
	void deleteAllByIdCandidate(int idCandidate);

	@Query("from WorkActivities "
			+ "where idCandidate = :idCandidate")
	List<WorkActivities> findAllByIdCandidate(int idCandidate);
}