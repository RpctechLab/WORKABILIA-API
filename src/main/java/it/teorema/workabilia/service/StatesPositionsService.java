package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.StatesPositions;

public interface StatesPositionsService extends JpaRepository <StatesPositions, Integer> {

	@Query("from StatesPositions "
			+ "where name != 'Chiusa'")
	List<StatesPositions> findAllExeptClose();
}