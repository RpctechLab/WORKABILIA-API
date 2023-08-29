package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ty_mansioni")
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_comparto")
	private Integer idCompartment;
	@Column(name = "nome")
	private String name;
	@Column(name = "esempi")
	private String examples;
	
	public Tasks() {}
	
	public Tasks(int id, Integer idCompartment, String name, String examples) {
		super();
		this.id = id;
		this.idCompartment = idCompartment;
		this.name = name;
		this.examples = examples;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCompartment() {
		return idCompartment;
	}

	public void setIdCompartment(Integer idCompartment) {
		this.idCompartment = idCompartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExamples() {
		return examples;
	}

	public void setExamples(String examples) {
		this.examples = examples;
	}
}