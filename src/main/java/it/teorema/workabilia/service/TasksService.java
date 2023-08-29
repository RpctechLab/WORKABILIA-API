package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.Tasks;

public interface TasksService extends JpaRepository <Tasks, Integer> {
}