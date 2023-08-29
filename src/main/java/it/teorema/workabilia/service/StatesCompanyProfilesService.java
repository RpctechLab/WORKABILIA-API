package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.StatesCompanyProfiles;
import jakarta.transaction.Transactional;

public interface StatesCompanyProfilesService extends JpaRepository <StatesCompanyProfiles, Integer> {
	
	@Query("select step "
            + "from StatesCompanyProfiles "
            + "where idCompany = :idCompany")
    Integer getStepByIdCompany(Integer idCompany);

	@Modifying
	@Transactional
	@Query("update StatesCompanyProfiles "
			+ "set step = :step "
			+ "where idCompany = :idCompany")
	void updateStep(Integer idCompany, Integer step);
}