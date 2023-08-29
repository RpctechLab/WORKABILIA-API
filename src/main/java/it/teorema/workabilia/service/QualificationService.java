package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.Qualifications;

public interface QualificationService extends JpaRepository <Qualifications, Integer> {
}