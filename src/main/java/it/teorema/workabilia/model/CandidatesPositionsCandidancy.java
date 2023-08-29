package it.teorema.workabilia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "as_candidati_posizioni_candidatura")
public class CandidatesPositionsCandidancy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "id_posizione")
	private Integer idPositioning;
	@Column(name = "data_candidatura")
	private LocalDateTime candidacyDate;
	
	public CandidatesPositionsCandidancy() {}
	
	public CandidatesPositionsCandidancy(int id, Integer idCandidate, Integer idPositioning, 
			LocalDateTime candidacyDate) {
		this.id = id;
		this.idCandidate = idCandidate;
		this.idPositioning = idPositioning;
		this.candidacyDate = candidacyDate;
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

	public Integer getIdPositioning() {
		return idPositioning;
	}

	public void setIdPositioning(Integer idPositioning) {
		this.idPositioning = idPositioning;
	}

	public LocalDateTime getCandidacyDate() {
		return candidacyDate;
	}

	public void setCandidacyDate(LocalDateTime candidacyDate) {
		this.candidacyDate = candidacyDate;
	}
	
}






