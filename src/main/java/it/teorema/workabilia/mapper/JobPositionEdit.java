package it.teorema.workabilia.mapper;

public class JobPositionEdit {
	private String name;
	private String code;
	private Integer idCompartment;
	private String task;
	private String description;
	private String activity;
	private Integer idProvince;
	
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
	public Integer getIdCompartment() {
		return idCompartment;
	}
	public void setIdCompartment(Integer idCompartment) {
		this.idCompartment = idCompartment;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getIdProvince() {
		return idProvince;
	}
	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}
}