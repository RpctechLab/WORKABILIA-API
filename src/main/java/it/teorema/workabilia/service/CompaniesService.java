package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Companies;
import jakarta.transaction.Transactional;

public interface CompaniesService extends JpaRepository <Companies, Integer>{
	
	@Query("from Companies "
			+ "where email = :email")
	Companies existByEmail(String email);
	
	@Query("from Companies "
			+ "where email = :email "
			+ "and id != :idCompany")
	Companies existByEmail(String email, Integer idCompany);
	
	@Query("SELECT c.id "
			+ "FROM Companies c")
	List<Integer> getAllIdCompanies ();
	
	@Query("select id "
			+ "from Companies "
			+ "where email = :email")
	int findIdByEmail(String email);
	
	@Modifying
	@Transactional
    @Query(value = "insert into az_aziende (denominazione, email, stato) "
    		+ "VALUES (:denomination, :email, 0)", nativeQuery = true)
    void insertCompanies(String denomination, String email);

	@Modifying
	@Transactional
    @Query("update Companies "
    		+ "set idLegalForm = :#{#company.idLegalForm}, idCompartment = :#{#company.idCompartment}, "
    		+ "denomination = :#{#company.denomination}, nEmployees = :#{#company.nEmployees}, "
    		+ "IVAnumber = :#{#company.IVAnumber}, resMunicipality = :#{#company.resMunicipality}, "
    		+ "resProvince = :#{#company.resProvince}, resNation = :#{#company.resNation}, "
    		+ "resAdress = :#{#company.resAdress}, resStreetNumber = :#{#company.resStreetNumber}, "
    		+ "resCap = :#{#company.resCap}, telephone = :#{#company.telephone}, pec = :#{#company.pec}, "
    		+ "email = :#{#company.email}, site = :#{#company.site} "
    		+ "where id = :idCompany")
	void updateCompany(Companies company, Integer idCompany);

	@Query("from Companies "
			+ "where id = :idCompany")
	Companies findByIdCompany(Integer idCompany);
}

















