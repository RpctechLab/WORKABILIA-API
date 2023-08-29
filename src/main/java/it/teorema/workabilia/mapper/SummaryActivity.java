package it.teorema.workabilia.mapper;

import it.teorema.workabilia.model.JobPositions;

public class SummaryActivity {

	JobPositions jobPosition;
	String error;
	
	public SummaryActivity(JobPositions jobPosition, String error) {
		this.jobPosition = jobPosition;
		this.error = error;
	}

	public JobPositions getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(JobPositions jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
