package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Instruction;
import jakarta.transaction.Transactional;

public interface InstructionService extends JpaRepository <Instruction, Integer> {

	@Query("from Instruction "
			+ "where idCandidate = :idCandidate")
	Instruction existByIdCandidate(int idCandidate);

	@Transactional
	@Modifying
	@Query("update Instruction "
			+ "set idQualification = :#{#istruction.idQualification}, yearGraduated = :#{#istruction.yearGraduated} "
			+ "where idCandidate = :#{#istruction.idCandidate}")
	void update(Instruction istruction);

	@Query("from Instruction "
			+ "where idCandidate = :idCandidate")
	Instruction findByIdCandidate(int idCandidate);
}