package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Compartments;

public interface CompartmentsService extends JpaRepository <Compartments, Integer> {

	@Query("select id "
			+ "from Compartments "
			+ "where name = :name")
	Integer findByName(String name);
	
	@Query("select idSector "
			+ "from Compartments "
			+ "where id = :IdCompartment")
	Integer findByIdCompartment(Integer IdCompartment);
	
	@Query("from Compartments "
			+ "where idSector =:idSector")
	List<Compartments> findByIdSector(Integer idSector);	
}