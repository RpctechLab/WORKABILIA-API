package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.Assignments;

public interface AssignmentsService extends JpaRepository<Assignments, Integer>{
}