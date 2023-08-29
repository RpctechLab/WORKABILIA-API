package it.teorema.workabilia.mapper;

public class ImportFileSettori {
	private String comparto;
	private String settore;
	public String getComparto() {
		return comparto;
	}
	public void setComparto(String comparto) {
		this.comparto = comparto;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	@Override
	public String toString() {
		return "ImportFile [comparto=" + comparto + ", settore=" + settore + "]";
	}
	
	
}