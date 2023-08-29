package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ca_dettagli_candidati")
public class CandidateDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "id_codice")
	private Integer idCode;
	@Column(name = "note")
	private String note;
	@Column(name = "spunta")
	private Boolean checkbox;
	
	public CandidateDetails() {}
	
	public CandidateDetails(Integer idCandidate, Integer idCode, String note, Boolean checkbox) {
		this.idCandidate = idCandidate;
		this.idCode = idCode;
		this.note = note;
		this.checkbox = checkbox;
	}

	public CandidateDetails(int id, Integer idCandidate, Integer idCode, String note, Boolean checkbox) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.idCode = idCode;
		this.note = note;
		this.checkbox = checkbox;
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

	public Integer getIdCode() {
		return idCode;
	}

	public void setIdCode(Integer idCode) {
		this.idCode = idCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}
}