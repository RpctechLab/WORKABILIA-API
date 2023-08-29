package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "as_candidati_posizioni_preferenza")
public class CandidatesPositionsPreference {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "id_posizione")
	private Integer idPosition;
	
	public CandidatesPositionsPreference() {}
	
	public CandidatesPositionsPreference(int id, Integer idCandidate, Integer idPosition) {
		this.id = id;
		this.idCandidate = idCandidate;
		this.idPosition = idPosition;
	}
	
	public CandidatesPositionsPreference(Integer idCandidate, Integer idPosition) {
		this.idCandidate = idCandidate;
		this.idPosition = idPosition;
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

	public Integer getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(Integer idPosition) {
		this.idPosition = idPosition;
	}
}