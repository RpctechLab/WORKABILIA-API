package it.teorema.workabilia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ca_candidati")
public class Candidates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nome")
	private String firstName;
	@Column(name = "cognome")
	private String lastName;
	@Column(name = "sesso")
	private Character gender;
	@Column(name = "nat_data")
	private LocalDate birthDate;
	@Column(name = "nat_luogo")
	private String birthPlace;
	@Column(name = "codice_fiscale")
	private String fiscalCode;
	@Column(name = "cittadinanza")
	private String citizenship;
	@Column(name = "residenza")
	private String residence;
	@Column(name = "domicilio")
	private String domicile;
	@Column(name = "telefono")
	private String telephone;
	@Column(name = "email")
	private String email;
	
	public Candidates() {}
	
	public Candidates(int id, String firstName, String lastName, Character gender, LocalDate birthDate, String birthPlace,
			String fiscalCode, String citizenship, String residence, String domicile, String telephone,
			String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.fiscalCode = fiscalCode;
		this.citizenship = citizenship;
		this.residence = residence;
		this.domicile = domicile;
		this.telephone = telephone;
		this.email = email;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}