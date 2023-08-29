package it.teorema.workabilia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hs_candidati_posizioni_candidatura_chiuse")
public class CandidatesPositionsCandidancyClosed {
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
	
	public CandidatesPositionsCandidancyClosed() {
}
	
	public CandidatesPositionsCandidancyClosed(int id, Integer idCandidate, Integer idPositioning,
			LocalDateTime candidacyDate) {

		this.id = id;
		this.idCandidate = idCandidate;
		this.idPositioning = idPositioning;
		this.candidacyDate = candidacyDate;
	}
	
	public CandidatesPositionsCandidancyClosed(CandidatesPositionsCandidancy cpc) {
		this.idCandidate = cpc.getIdCandidate();
		this.idPositioning = cpc.getIdPositioning();
		this.candidacyDate = cpc.getCandidacyDate();
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