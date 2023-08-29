package it.teorema.workabilia.mapper;

public class JobPositionMatch implements Comparable<JobPositionMatch> {
	
	Integer percentage;
	FindAllJobPositions jobPosition;
	Boolean favourite;
	
	public JobPositionMatch(Integer percentage, FindAllJobPositions jobPosition,Boolean favourite) {
		this.percentage = percentage;
		this.jobPosition = jobPosition;
		this.favourite = favourite;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public FindAllJobPositions getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(FindAllJobPositions jobPosition) {
		this.jobPosition = jobPosition;
	}
	
	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}
	
	@Override
	public int compareTo(JobPositionMatch jpm) {
		if(getPercentage() == null || jpm.getPercentage() == null) {
			return 0;
		}
		return getPercentage().compareTo(jpm.getPercentage());
	}
	
}
