package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.LegalRepresentatives;
import jakarta.transaction.Transactional;

public interface LegalRepresentativesService extends JpaRepository <LegalRepresentatives, Integer>{

	@Modifying
	@Transactional
	@Query("update LegalRepresentatives "
			+ "set idAssignment = :#{#legalRepresentatives.idAssignment}, "
			+ "firstName = :#{#legalRepresentatives.firstName}, "
			+ "lastName = :#{#legalRepresentatives.lastName}, "
			+ "fiscalCode = :#{#legalRepresentatives.fiscalCode}, "
			+ "gender = :#{#legalRepresentatives.gender}, "
			+ "birthNation = :#{#legalRepresentatives.birthNation}, "
			+ "birthProfince = :#{#legalRepresentatives.birthProfince}, "
			+ "birthDate = :#{#legalRepresentatives.birthDate}, "
			+ "citizenship = :#{#legalRepresentatives.citizenship}, "
			+ "resCountry = :#{#legalRepresentatives.resCountry}, "
			+ "resProvince = :#{#legalRepresentatives.resProvince}, "
			+ "resAddress = :#{#legalRepresentatives.resAddress}, "
			+ "resCivic = :#{#legalRepresentatives.resCivic}, "
			+ "resCap = :#{#legalRepresentatives.resCap}, "
			+ "pec = :#{#legalRepresentatives.pec}, "
			+ "email = :#{#legalRepresentatives.email}, "
			+ "phone = :#{#legalRepresentatives.phone} "
			+ "where idCompany = :#{#legalRepresentatives.idCompany}")
	void updateCompany(LegalRepresentatives legalRepresentatives);

	@Query("from LegalRepresentatives where idCompany = :idCompany")
	LegalRepresentatives findByIdCompany(Integer idCompany);
}