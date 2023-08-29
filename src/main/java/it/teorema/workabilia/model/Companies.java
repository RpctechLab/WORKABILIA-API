package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "az_aziende")
public class Companies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_forma_giuridica")
	private Integer idLegalForm;
	@Column(name = "id_comparto")
	private Integer idCompartment;
	@Column(name = "denominazione")
	private String denomination;
	@Column(name = "partita_iva")
	private String IVAnumber;
	@Column(name = "res_comune")
	private String resMunicipality;
	@Column(name = "res_provincia")
	private String resProvince;
	@Column(name = "res_nazione")
	private String resNation;
	@Column(name = "res_indirizzo")
	private String resAdress;
	@Column(name = "res_civico")
	private String resStreetNumber;
	@Column(name = "res_cap")
	private String resCap;
	@Column(name = "telefono")
	private String telephone;
	@Column(name = "pec")
	private String pec;
	@Column(name = "email")
	private String email;
	@Column(name = "sito")
	private String site;
	@Column(name = "stato")
	private String status;
	@Column(name = "n_dipendenti")
	private Integer nEmployees;
	
	public Companies() {}

	public Companies(int id, Integer idLegalForm, Integer idCompartment, String denomination, String iVAnumber,
			String resMunicipality, String resProvince, String resNation, String resAdress, String resStreetNumber,
			String resCap, String telephone, String pec, String email, String site, String status, Integer nEmployees) {
		this.id = id;
		this.idLegalForm = idLegalForm;
		this.idCompartment = idCompartment;
		this.denomination = denomination;
		IVAnumber = iVAnumber;
		this.resMunicipality = resMunicipality;
		this.resProvince = resProvince;
		this.resNation = resNation;
		this.resAdress = resAdress;
		this.resStreetNumber = resStreetNumber;
		this.resCap = resCap;
		this.telephone = telephone;
		this.pec = pec;
		this.email = email;
		this.site = site;
		this.status = status;
		this.nEmployees = nEmployees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdLegalForm() {
		return idLegalForm;
	}

	public void setIdLegalForm(Integer idLegalForm) {
		this.idLegalForm = idLegalForm;
	}

	public Integer getIdCompartment() {
		return idCompartment;
	}

	public void setIdCompartment(Integer idCompartment) {
		this.idCompartment = idCompartment;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getIVAnumber() {
		return IVAnumber;
	}

	public void setIVAnumber(String iVAnumber) {
		IVAnumber = iVAnumber;
	}

	public String getResMunicipality() {
		return resMunicipality;
	}

	public void setResMunicipality(String resMunicipality) {
		this.resMunicipality = resMunicipality;
	}

	public String getResProvince() {
		return resProvince;
	}

	public void setResProvince(String resProvince) {
		this.resProvince = resProvince;
	}

	public String getResNation() {
		return resNation;
	}

	public void setResNation(String resNation) {
		this.resNation = resNation;
	}

	public String getResAdress() {
		return resAdress;
	}

	public void setResAdress(String resAdress) {
		this.resAdress = resAdress;
	}

	public String getResStreetNumber() {
		return resStreetNumber;
	}

	public void setResStreetNumber(String resStreetNumber) {
		this.resStreetNumber = resStreetNumber;
	}

	public String getResCap() {
		return resCap;
	}

	public void setResCap(String resCap) {
		this.resCap = resCap;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getnEmployees() {
		return nEmployees;
	}

	public void setnEmployees(Integer nEmployees) {
		this.nEmployees = nEmployees;
	}

	
}