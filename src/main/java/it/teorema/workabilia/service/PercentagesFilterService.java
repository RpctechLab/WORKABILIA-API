package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;

import it.teorema.workabilia.model.PercentagesFilter;

public interface PercentagesFilterService extends JpaRepository <PercentagesFilter, Integer> {

}
