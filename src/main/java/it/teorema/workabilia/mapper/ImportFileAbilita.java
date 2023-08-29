package it.teorema.workabilia.mapper;

public class ImportFileAbilita {
	private String codice1;
	private String codice2;
	private String codice3;
	private String codice4;
	private String nome;
	private String domanda;
	public String getCodice1() {
		return codice1;
	}
	public void setCodice1(String codice1) {
		this.codice1 = codice1;
	}
	public String getCodice2() {
		return codice2;
	}
	public void setCodice2(String codice2) {
		this.codice2 = codice2;
	}
	public String getCodice3() {
		return codice3;
	}
	public void setCodice3(String codice3) {
		this.codice3 = codice3;
	}
	public String getCodice4() {
		return codice4;
	}
	public void setCodice4(String codice4) {
		this.codice4 = codice4;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDomanda() {
		return domanda;
	}
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}
	@Override
	public String toString() {
		return "ImportFileAbilita [codice1=" + codice1 + ", codice2=" + codice2 + ", codice3=" + codice3 + ", codice4="
				+ codice4 + ", nome=" + nome + ", domanda=" + domanda + "]";
	}
}