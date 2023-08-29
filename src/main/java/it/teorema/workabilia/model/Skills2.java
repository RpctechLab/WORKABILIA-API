package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pl_abilita_2")
public class Skills2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_abilita_1")
	private Integer idSkill1;
	@Column(name = "codice")
	private String code;
	@Column(name = "nome")
	private String name;
	
	public Skills2() {}

	public Skills2(int id, Integer idSkill1, String code, String name) {
		super();
		this.id = id;
		this.idSkill1 = idSkill1;
		this.code = code;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdSkill1() {
		return idSkill1;
	}

	public void setIdSkill1(Integer idSkill1) {
		this.idSkill1 = idSkill1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}