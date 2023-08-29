package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ca_istruzione")
public class Instruction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "id_titolo_di_studio")
	private Integer idQualification;
	@Column(name = "anno_conseguimento")
	private String yearGraduated;
	
	public Instruction() {}

	public Instruction(int id, Integer idCandidate, Integer idQualification, String yearGraduated) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.idQualification = idQualification;
		this.yearGraduated = yearGraduated;
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

	public Integer getIdQualification() {
		return idQualification;
	}

	public void setIdQualification(Integer idQualification) {
		this.idQualification = idQualification;
	}

	public String getYearGraduated() {
		return yearGraduated;
	}

	public void setYearGraduated(String yearGraduated) {
		this.yearGraduated = yearGraduated;
	}
}