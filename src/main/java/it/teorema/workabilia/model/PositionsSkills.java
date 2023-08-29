package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "as_posizioni_abilita")
public class PositionsSkills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_posizione")
	private Integer idPositions;
	@Column(name = "codice_abilita")
	private String skillCode;
	@Column(name = "nome_supporto")
	private String supportName;
	@Column(name = "descrizione_supporto")
	private String supportDescription;
	
	public PositionsSkills() {}

	public PositionsSkills(int id, Integer idPositions, String skillCode, String supportName, String supportDescription) {
		super();
		this.id = id;
		this.idPositions = idPositions;
		this.skillCode = skillCode;
		this.supportName = supportName;
		this.supportDescription = supportDescription;
	}
	
	public PositionsSkills(Integer idPositions, String skillCode, String supportName, String supportDescription) {
		this.idPositions = idPositions;
		this.skillCode = skillCode;
		this.supportName = supportName;
		this.supportDescription = supportDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdPositions() {
		return idPositions;
	}

	public void setIdPositions(Integer idPositions) {
		this.idPositions = idPositions;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

	public String getSupportName() {
		return supportName;
	}

	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}

	public String getSupportDescription() {
		return supportDescription;
	}

	public void setSupportDescription(String supportDescription) {
		this.supportDescription = supportDescription;
	}

	@Override
	public String toString() {
		return "PositionsSkills [id=" + id + ", idPositions=" + idPositions + ", skillCode=" + skillCode
				+ ", supportName=" + supportName + ", supportDescription=" + supportDescription + "]";
	}

}