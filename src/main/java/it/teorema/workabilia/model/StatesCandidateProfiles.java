package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ca_stati_profili_candidati")
public class StatesCandidateProfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "step")
	private Integer step;
	
	public StatesCandidateProfiles() {}

	public StatesCandidateProfiles(int id, Integer idCandidate, Integer step) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.step = step;
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

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}