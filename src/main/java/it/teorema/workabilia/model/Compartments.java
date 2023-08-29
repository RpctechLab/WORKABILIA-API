package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ty_comparti")
public class Compartments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_settore")
	private Integer idSector;
	@Column(name = "nome")
	private String name;
	
	public Compartments() {}
	
	public Compartments(int id, Integer idSector, String name) {
		this.id = id;
		this.idSector = idSector;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}