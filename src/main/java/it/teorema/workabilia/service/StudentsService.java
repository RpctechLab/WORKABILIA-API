package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Students;
import jakarta.transaction.Transactional;

public interface StudentsService extends JpaRepository <Students, Integer> {

	@Query("from Students "
			+ "where idCandidate = :idCandidate")
	Students existByIdCandidate(int idCandidate);

	@Transactional
	@Modifying
	@Query("update Students "
			+ "set nameInstitute = :#{#student.nameInstitute}, address = :#{#student.address}, "
			+ "schoolClass = :#{#student.schoolClass}, schoolDelay = :#{#student.schoolDelay}, "
			+ "reasonDelay = :#{#student.reasonDelay}, teachingSupport = :#{#student.teachingSupport}, "
			+ "teachingSupportHours = :#{#student.teachingSupportHours}, specialistAssistance = :#{#student.specialistAssistance}, "
			+ "specialistAssistanceHours = :#{#student.specialistAssistanceHours}, universityTutor = :#{#student.universityTutor} "
			+ "where idCandidate = :#{#student.idCandidate}")
	void update(Students student);

	@Query("from Students "
			+ "where idCandidate = :idCandidate")
	Students findByIdCandidate(int idCandidate);
}