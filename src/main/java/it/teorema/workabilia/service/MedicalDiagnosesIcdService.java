package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.MedicalDiagnosesIcd;
import jakarta.transaction.Transactional;

public interface MedicalDiagnosesIcdService extends JpaRepository <MedicalDiagnosesIcd, Integer> {

	@Query("from MedicalDiagnosesIcd "
			+ "where idCandidate = :idCandidate "
			+ "group by idCandidate")
	MedicalDiagnosesIcd existByIdCandidate(int idCandidate);

	@Modifying
	@Transactional
	@Query("delete from MedicalDiagnosesIcd "
			+ "where idCandidate = :idCandidate")
	void deleteAllByIdCandidate(int idCandidate);

	@Query("from MedicalDiagnosesIcd "
			+ "where idCandidate = :idCandidate")
	List<MedicalDiagnosesIcd> findAllByIdCandidate(int idCandidate);
}