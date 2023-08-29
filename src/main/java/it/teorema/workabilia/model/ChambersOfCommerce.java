package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "az_camere_di_commercio")
public class ChambersOfCommerce {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_azienda")
	private Integer idCompany;
	@Column(name = "citta")
	private String city;
	@Column(name = "provincia")
	private String province;
	@Column(name = "n_rea")
	private String reaNumber;
	
	public ChambersOfCommerce() {}

	public ChambersOfCommerce(int id, Integer idCompany, String city, String province, String reaNumber) {
		super();
		this.id = id;
		this.idCompany = idCompany;
		this.city = city;
		this.province = province;
		this.reaNumber = reaNumber;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getReaNumber() {
		return reaNumber;
	}

	public void setReaNumber(String reaNumber) {
		this.reaNumber = reaNumber;
	}

	@Override
	public String toString() {
		return "ChambersOfCommerce [id=" + id + ", idCompany=" + idCompany + ", city=" + city + ", province=" + province
				+ ", reaNumber=" + reaNumber + "]";
	}
	
	
}