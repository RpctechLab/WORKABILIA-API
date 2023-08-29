package it.teorema.workabilia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ap_auth")
public class Auth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_user")
	private Integer idUser;
	@Column(name = " password")
	private String password;
	@Column(name = "data")
	private LocalDate date;
	
	public Auth() {}
	
	public Auth(Integer idUser, String password, LocalDate date) {
		this.idUser = idUser;
		this.password = password;
		this.date = date;
	}
	
	public Auth(int id, Integer idUser, String password, LocalDate date) {
		this.id = id;
		this.idUser = idUser;
		this.password = password;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}