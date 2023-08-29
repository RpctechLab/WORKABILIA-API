package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.Roles;

public interface RolesService extends JpaRepository <Roles, Integer> {
}