package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.ChambersOfCommerce;
import jakarta.transaction.Transactional;

public interface ChambersOfCommerceService extends JpaRepository <ChambersOfCommerce, Integer> {

	@Modifying
	@Transactional
	@Query("update ChambersOfCommerce "
			+ "set city = :#{#chamberOfCommerce.city}, province = :#{#chamberOfCommerce.province}, "
			+ "reaNumber = :#{#chamberOfCommerce.reaNumber} "
			+ "where idCompany = :idCompany")
	void updateCompany(ChambersOfCommerce chamberOfCommerce, Integer idCompany);
	
	@Query("from ChambersOfCommerce where idCompany = :idCompany")
	ChambersOfCommerce findByIdCompany(Integer idCompany);

	@Transactional
	@Modifying
	@Query("delete from ChambersOfCommerce "
			+ "where idCompany = :idCompany")
	void deleteByIdCompany(Integer idCompany);
}