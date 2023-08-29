package it.teorema.workabilia.mapper;

public class SummarySkills {
	String code;
	String name;
	String error;
	
	public SummarySkills() {}

	public SummarySkills(String code, String name,  String error) {
		this.code = code;
		this.name = name;
		this.error = error;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
