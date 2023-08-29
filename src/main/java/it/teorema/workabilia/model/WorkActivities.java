package it.teorema.workabilia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ca_attivita_lavorative")
public class WorkActivities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "nome_azienda")
	private String companyName;
	@Column(name = "data_inizio")
	private LocalDate startDate;
	@Column(name = " data_fine")
	private LocalDate endDate;
	@Column(name = "mansione")
	private String task;
	
	public WorkActivities() {}

	public WorkActivities(int id, Integer idCandidate, String companyName, LocalDate startDate, LocalDate endDate,
			String task) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.task = task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}