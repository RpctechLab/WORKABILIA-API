package it.teorema.workabilia.mapper;

public class JobPositionCount {

	Integer candidacy;
	FindAllJobPositions jobPosition;
	
	public JobPositionCount() {}

	public JobPositionCount(Integer candidacy, FindAllJobPositions jobPosition) {
		this.candidacy = candidacy;
		this.jobPosition = jobPosition;
	}

	public Integer getCandidacy() {
		return candidacy;
	}

	public void setCandidacy(Integer candidacy) {
		this.candidacy = candidacy;
	}

	public FindAllJobPositions getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(FindAllJobPositions jobPosition) {
		this.jobPosition = jobPosition;
	}
	

}
