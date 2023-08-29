package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.CandidatesInfoCodes;

public interface CandidateInfoCodesService extends JpaRepository <CandidatesInfoCodes, Integer> {
}