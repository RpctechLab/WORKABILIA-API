package it.teorema.workabilia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ca_studenti")
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "id_candidato")
	private Integer idCandidate;
	@Column(name = "nome_istituto")
	private String nameInstitute;
	@Column(name = "indirizzo")
	private String address;
	@Column(name = "classe")
	private String schoolClass;
	@Column(name = "ritardo_scolastico")
	private Boolean schoolDelay;
	@Column(name = "motivazione_ritardo")
	private String reasonDelay;
	@Column(name = "sostegno_didattico")
	private Boolean teachingSupport;
	@Column(name = "ore_sostegno_didattico")
	private String teachingSupportHours;
	@Column(name = "assistenza_specialistica")
	private Boolean specialistAssistance;
	@Column(name = "ore_assistenza_specialistica")
	private String specialistAssistanceHours;
	@Column(name = "tutor_universitario")
	private Boolean universityTutor;
	
	public Students() {}

	public Students(int id, Integer idCandidate, String nameInstitute, String address, String schoolClass,
			Boolean schoolDelay, String reasonDelay, Boolean teachingSupport, String teachingSupportHours,
			Boolean specialistAssistance, String specialistAssistanceHours, Boolean universityTutor) {
		super();
		this.id = id;
		this.idCandidate = idCandidate;
		this.nameInstitute = nameInstitute;
		this.address = address;
		this.schoolClass = schoolClass;
		this.schoolDelay = schoolDelay;
		this.reasonDelay = reasonDelay;
		this.teachingSupport = teachingSupport;
		this.teachingSupportHours = teachingSupportHours;
		this.specialistAssistance = specialistAssistance;
		this.specialistAssistanceHours = specialistAssistanceHours;
		this.universityTutor = universityTutor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
	}

	public String getNameInstitute() {
		return nameInstitute;
	}

	public void setNameInstitute(String nameInstitute) {
		this.nameInstitute = nameInstitute;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}

	public Boolean isSchoolDelay() {
		return schoolDelay;
	}

	public void setSchoolDelay(Boolean schoolDelay) {
		this.schoolDelay = schoolDelay;
	}

	public String getReasonDelay() {
		return reasonDelay;
	}

	public void setReasonDelay(String reasonDelay) {
		this.reasonDelay = reasonDelay;
	}

	public Boolean isTeachingSupport() {
		return teachingSupport;
	}

	public void setTeachingSupport(Boolean teachingSupport) {
		this.teachingSupport = teachingSupport;
	}

	public String getTeachingSupportHours() {
		return teachingSupportHours;
	}

	public void setTeachingSupportHours(String teachingSupportHours) {
		this.teachingSupportHours = teachingSupportHours;
	}

	public Boolean isSpecialistAssistance() {
		return specialistAssistance;
	}

	public void setSpecialistAssistance(Boolean specialistAssistance) {
		this.specialistAssistance = specialistAssistance;
	}

	public String getSpecialistAssistanceHours() {
		return specialistAssistanceHours;
	}

	public void setSpecialistAssistanceHours(String specialistAssistanceHours) {
		this.specialistAssistanceHours = specialistAssistanceHours;
	}

	public Boolean isUniversityTutor() {
		return universityTutor;
	}

	public void setUniversityTutor(Boolean universityTutor) {
		this.universityTutor = universityTutor;
	}
}