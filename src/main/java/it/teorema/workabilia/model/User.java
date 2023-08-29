package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ap_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_ruolo")
	private Integer idRole;
	@Column(name = "id_entity")
	private Integer idEntity;
	@Column(name = "stato")
	private Integer state;
	
	public User() {}
	
	public User(Integer idRole, Integer idEntity) {
		this.idRole = idRole;
		this.idEntity = idEntity;
	}
	
	public User(Integer idRole, Integer idEntity, Integer state) {
		this.idRole = idRole;
		this.idEntity = idEntity;
		this.state = state;
	}
	
	public User(int id, Integer idRole, Integer idEntity, Integer state) {
		this.id = id;
		this.idRole = idRole;
		this.idEntity = idEntity;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Integer getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}
}