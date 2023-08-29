package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "as_candidati_social")
public class CandidatesSocial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name="id_social")
	private Integer idSocial;
	@Column(name = "link")
	private String link;
	
	public CandidatesSocial() {}
	
	public CandidatesSocial(int id, Integer idCandidate, Integer idSocial, String link) {
		this.id = id;
		this.idCandidate = idCandidate;
		this.idSocial = idSocial;
		this.link = link;
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

	public Integer getIdSocial() {
		return idSocial;
	}

	public void setIdSocial(Integer idSocial) {
		this.idSocial = idSocial;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}