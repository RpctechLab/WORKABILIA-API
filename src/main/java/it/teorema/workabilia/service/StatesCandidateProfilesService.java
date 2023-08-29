package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.StatesCandidateProfiles;
import jakarta.transaction.Transactional;

public interface StatesCandidateProfilesService extends JpaRepository <StatesCandidateProfiles, Integer> {

    @Query("select step "
            + "from StatesCandidateProfiles "
            + "where idCandidate = :idCandidate")
    Integer getStepByIdCandidate(Integer idCandidate);

    @Modifying
    @Transactional
    @Query("update StatesCandidateProfiles "
            + "set step = :newStep "
            + "where idCandidate = :idCandidate")
    void updateStep(int idCandidate,int newStep);
}