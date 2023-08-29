package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pl_abilita_4")
public class Skills4 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_abilita_3")
	private Integer idSkill3;
	@Column(name = "codice")
	private String code;
	@Column(name = "nome")
	private String name;
	@Column(name = "domanda")
	private String question;
	
	public Skills4() {}

	public Skills4(int id, Integer idSkill3, String code, String name, String question) {
		super();
		this.id = id;
		this.idSkill3 = idSkill3;
		this.code = code;
		this.name = name;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdSkill3() {
		return idSkill3;
	}

	public void setIdSkill3(Integer idSkill3) {
		this.idSkill3 = idSkill3;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}