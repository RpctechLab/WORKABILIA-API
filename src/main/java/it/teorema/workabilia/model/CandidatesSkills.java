package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "as_candidati_abilita")
public class CandidatesSkills {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column( name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "codice_abilita")
	private String skillCode;
	
	public CandidatesSkills() {}
	
	public CandidatesSkills(int id, Integer idCandidate, String skillCode) {
		this.id = id;
		this.idCandidate = idCandidate;
		this.skillCode = skillCode;
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

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
}