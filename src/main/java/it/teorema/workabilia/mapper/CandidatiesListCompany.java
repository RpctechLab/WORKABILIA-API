package it.teorema.workabilia.mapper;

import java.time.LocalDateTime;

public interface CandidatiesListCompany {
	Integer getId();
	String getFirstName();
	String getLastName();
	String getJobName();
	LocalDateTime getCandidacyDate();
	Integer getPercentage();
}
