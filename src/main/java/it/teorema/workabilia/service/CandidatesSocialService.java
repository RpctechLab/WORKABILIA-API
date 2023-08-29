package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.CandidatesSocial;

public interface CandidatesSocialService extends JpaRepository <CandidatesSocial, Integer>  {
}