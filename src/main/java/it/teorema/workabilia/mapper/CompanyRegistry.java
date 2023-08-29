package it.teorema.workabilia.mapper;

import it.teorema.workabilia.model.ChambersOfCommerce;
import it.teorema.workabilia.model.Companies;

public class CompanyRegistry {
	
	private Companies company;
	private Integer idSector;
	private ChambersOfCommerce chamberOfCommerce;
	
	public Companies getCompany() {
		return company;
	}
	public void setCompany(Companies company) {
		this.company = company;
	}
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public ChambersOfCommerce getChamberOfCommerce() {
		return chamberOfCommerce;
	}
	public void setChamberOfCommerce(ChambersOfCommerce chamberOfCommerce) {
		this.chamberOfCommerce = chamberOfCommerce;
	}
}