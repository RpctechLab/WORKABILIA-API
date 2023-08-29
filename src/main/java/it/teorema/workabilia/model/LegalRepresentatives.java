package it.teorema.workabilia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "az_rappresentanti_legali")
public class LegalRepresentatives {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_azienda")
	private Integer idCompany;
	@Column(name = "id_incarico")
	private Integer idAssignment;
	@Column(name = "nome")
	private String firstName;
	@Column(name = "cognome")
	private String lastName;
	@Column(name = "codice_fiscale")
	private String fiscalCode;
	@Column(name = "sesso")
	private Character gender;
	@Column(name = "nat_nazione")
	private String birthNation;
	@Column(name = "nat_provincia")
	private String birthProfince;
	@Column(name = "nat_data")
	private LocalDate birthDate;
	@Column(name = "cittadinanza")
	private String citizenship;
	@Column(name = "res_nazione")
	private String resCountry;
	@Column(name = "res_provincia")
	private String resProvince;
	@Column(name = "res_indirizzo")
	private String resAddress;
	@Column(name = "res_civico")
	private String resCivic;
	@Column(name = "res_cap")
	private String resCap;
	@Column(name = "pec")
	private String pec;
	@Column(name = "email")
	private String email;
	@Column(name = "telefono")
	private String phone;
	
	public LegalRepresentatives() {}

	public LegalRepresentatives(int id, Integer idCompany, Integer idAssignment, String firstName, String lastName,
			String fiscalCode, Character gender, String birthNation, String birthProfince, LocalDate birthDate,
			String citizenship, String resCountry, String resProvince, String resAddress, String resCivic,
			String resCap, String pec, String email, String phone) {
		super();
		this.id = id;
		this.idCompany = idCompany;
		this.idAssignment = idAssignment;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.gender = gender;
		this.birthNation = birthNation;
		this.birthProfince = birthProfince;
		this.birthDate = birthDate;
		this.citizenship = citizenship;
		this.resCountry = resCountry;
		this.resProvince = resProvince;
		this.resAddress = resAddress;
		this.resCivic = resCivic;
		this.resCap = resCap;
		this.pec = pec;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public Integer getIdAssignment() {
		return idAssignment;
	}

	public void setIdAssignment(Integer idAssignment) {
		this.idAssignment = idAssignment;
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

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getBirthNation() {
		return birthNation;
	}

	public void setBirthNation(String birthNation) {
		this.birthNation = birthNation;
	}

	public String getBirthProfince() {
		return birthProfince;
	}

	public void setBirthProfince(String birthProfince) {
		this.birthProfince = birthProfince;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getResCountry() {
		return resCountry;
	}

	public void setResCountry(String resCountry) {
		this.resCountry = resCountry;
	}

	public String getResProvince() {
		return resProvince;
	}

	public void setResProvince(String resProvince) {
		this.resProvince = resProvince;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getResCivic() {
		return resCivic;
	}

	public void setResCivic(String resCivic) {
		this.resCivic = resCivic;
	}

	public String getResCap() {
		return resCap;
	}

	public void setResCap(String resCap) {
		this.resCap = resCap;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}