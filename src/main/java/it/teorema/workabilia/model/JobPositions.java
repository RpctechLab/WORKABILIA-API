package it.teorema.workabilia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pl_posizioni_lavorative")
public class JobPositions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_user_creatore")
	private Integer idCreatorUser;
	@Column(name = "id_stato")
	private Integer idState;
	@Column(name = "id_comparto")
	private Integer idCompartment;
	@Column(name = "codice")
	private String code;
	@Column(name = "mansione")
	private String task;
	@Column(name = "denominazione")
	private String name;
	@Column(name = "descrizione")
	private String description;
	@Column(name = "attivita")
	private String activity;
	@Column(name = "id_provincia")
	private Integer idProvince;
	@Column(name = "data_inserimento")
	private LocalDateTime dateEntered;
	@Column(name = "data_ultima_modifica")
	private LocalDateTime lastModificationDate;
	
	public JobPositions() {}
	
	public JobPositions(int id, Integer idCreatorUser, Integer idState, Integer idCompartment, String code,
			String task, String name, String description, String activity, Integer idProvince,
			LocalDateTime dateEntered, LocalDateTime lastModificationDate) {
		this.id = id;
		this.idCreatorUser = idCreatorUser;
		this.idState = idState;
		this.idCompartment = idCompartment;
		this.code = code;
		this.task = task;
		this.name = name;
		this.description = description;
		this.activity = activity;
		this.idProvince = idProvince;
		this.dateEntered = dateEntered;
		this.lastModificationDate = lastModificationDate;
	}
	
	public JobPositions(Integer idCreatorUser, Integer idState, Integer idCompartment, String code,
			String task, String name, String description, String activity, Integer idProvince,
			LocalDateTime dateEntered, LocalDateTime lastModificationDate) {
		this.idCreatorUser = idCreatorUser;
		this.idState = idState;
		this.idCompartment = idCompartment;
		this.code = code;
		this.task = task;
		this.name = name;
		this.description = description;
		this.activity = activity;
		this.idProvince = idProvince;
		this.dateEntered = dateEntered;
		this.lastModificationDate = lastModificationDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getIdCreatorUser() {
		return idCreatorUser;
	}
	public void setIdCreatorUser(Integer idCreatorUser) {
		this.idCreatorUser = idCreatorUser;
	}
	public Integer getIdState() {
		return idState;
	}
	public void setIdState(Integer idState) {
		this.idState = idState;
	}
	public Integer getIdCompartment() {
		return idCompartment;
	}
	public void setIdCompartment(Integer idCompartment) {
		this.idCompartment = idCompartment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public LocalDateTime getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(LocalDateTime dateEntered) {
		this.dateEntered = dateEntered;
	}
	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}
	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	
	
	
}