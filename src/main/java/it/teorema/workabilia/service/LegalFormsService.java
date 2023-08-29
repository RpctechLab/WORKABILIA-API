package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.LegalForms;

public interface LegalFormsService extends JpaRepository <LegalForms, Integer> {
}