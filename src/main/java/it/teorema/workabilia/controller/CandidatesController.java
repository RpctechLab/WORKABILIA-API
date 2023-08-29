package it.teorema.workabilia.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.teorema.workabilia.mapper.InstructionStudent;
import it.teorema.workabilia.model.CandidateDetails;
import it.teorema.workabilia.model.Candidates;
import it.teorema.workabilia.model.CandidatesSkills;
import it.teorema.workabilia.model.Contacts;
import it.teorema.workabilia.model.Guardians;
import it.teorema.workabilia.model.MedicalDiagnosesIcd;
import it.teorema.workabilia.model.WorkActivities;
import it.teorema.workabilia.service.AdminService;
import it.teorema.workabilia.service.AuthService;
import it.teorema.workabilia.service.CandidateDetailsService;
import it.teorema.workabilia.service.CandidatesService;
import it.teorema.workabilia.service.CandidatesSkillsService;
import it.teorema.workabilia.service.CompaniesService;
import it.teorema.workabilia.service.ContactsService;
import it.teorema.workabilia.service.GuardiansService;
import it.teorema.workabilia.service.InstructionService;
import it.teorema.workabilia.service.MedicalDiagnosesIcdService;
import it.teorema.workabilia.service.StatesCandidateProfilesService;
import it.teorema.workabilia.service.StudentsService;
import it.teorema.workabilia.service.WorkActivitiesService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/candidates")
public class CandidatesController {
	
	@Autowired
	AuthService authService;
	@Autowired
	CompaniesService companiesService;
	@Autowired
	AdminService adminService;
	@Autowired
	CandidatesService candidatesService;
	@Autowired
	InstructionService instructionService;
	@Autowired
	StudentsService studentsService;
	@Autowired
	MedicalDiagnosesIcdService medicalDiagnosesIcdService;
	@Autowired
	CandidateDetailsService candidateDetailsService;
	@Autowired
	WorkActivitiesService workActivitiesService;
	@Autowired
	GuardiansService guardiansService;
	@Autowired
	ContactsService contactsService;
	@Autowired
	StatesCandidateProfilesService stateCandidateProfilesService;
	@Autowired
	CandidatesSkillsService candidatesSkillsService;
	
	@Autowired
	SecurityController securityController;

	@PostMapping("/update-candidate-step-1/{idCandidate}")
	public ResponseEntity<ResponseHttp> updateCandidateStep1 (@RequestBody Candidates candidate,
																@PathVariable("idCandidate") int idCandidate,
																HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if (candidatesService.existsByEmailCandidates(candidate.getEmail(), idCandidate) != null || 
				companiesService.existByEmail(candidate.getEmail()) != null ||
				adminService.existByEmail(candidate.getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else { 
			candidatesService.updateCandidate(candidate, idCandidate);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 1) {
				stateCandidateProfilesService.updateStep(idCandidate, 2);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-candidate-step-2")
	public ResponseEntity<ResponseHttp> updateCandidateStep2 (@RequestBody InstructionStudent instructionStudent,
																HttpServletRequest request) {		
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if (instructionService.existByIdCandidate(instructionStudent.getInstruction().getIdCandidate()) != null) {
			instructionService.update(instructionStudent.getInstruction());
			if (studentsService.existByIdCandidate(instructionStudent.getInstruction().getIdCandidate()) == null && instructionStudent.getStudent() != null) {
				studentsService.save(instructionStudent.getStudent());
			}
			else {
				studentsService.update(instructionStudent.getStudent());
			}
		}
		else {
			instructionService.save(instructionStudent.getInstruction());
			if (instructionStudent.getStudent() != null) {
				studentsService.save(instructionStudent.getStudent());
			}	
		}
		if(stateCandidateProfilesService.getStepByIdCandidate(instructionStudent.getInstruction().getIdCandidate()) == 2) {
			stateCandidateProfilesService.updateStep(instructionStudent.getInstruction().getIdCandidate(),3);
		}
		responseHttp.setCode("1");
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/update-candidate-step-3")
	public ResponseEntity<ResponseHttp> updateCandidateStep3 (@RequestBody List<MedicalDiagnosesIcd> arrayMecicalDiagnosis, 
																HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			int idCandidato = arrayMecicalDiagnosis.get(0).getIdCandidate();
			medicalDiagnosesIcdService.deleteAllByIdCandidate(idCandidato);
			medicalDiagnosesIcdService.saveAll(arrayMecicalDiagnosis);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidato) == 3) {
				stateCandidateProfilesService.updateStep(idCandidato, 4);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);	
		}
	}
	
	@PostMapping("/update-candidate-step-4")
	public ResponseEntity<ResponseHttp> updateCandidateStep4 (@RequestBody List<CandidateDetails> candidateDetails,
																HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			int idCandidate = candidateDetails.get(0).getIdCandidate();
			candidateDetailsService.deleteAllByIdCandidate(idCandidate);
			candidateDetailsService.saveAll(candidateDetails);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 4) {
				stateCandidateProfilesService.updateStep(idCandidate, 5);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp,HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-candidate-step-5")
	public ResponseEntity<ResponseHttp> updateCandidateStep5 (@RequestBody List<WorkActivities> workActivities, 
																HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			int idCandidate = workActivities.get(0).getIdCandidate();
			workActivitiesService.deleteAllByIdCandidate(idCandidate);
			workActivitiesService.saveAll(workActivities);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 5) {
				stateCandidateProfilesService.updateStep(idCandidate, 6);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-candidate-step-6")
	public ResponseEntity<ResponseHttp> updateCandidateStep6 (@RequestBody Guardians guardian, HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			int idCandidate = guardian.getIdCandidate();
			if(guardiansService.existsByIdCandidate(idCandidate) != null) {
				guardiansService.updateGuardians(guardian);
			}
			else {
				guardiansService.save(guardian);
			}
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 6) {
				stateCandidateProfilesService.updateStep(idCandidate,7);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-candidate-step-7")
	public ResponseEntity<ResponseHttp> updateCandidateStep7 (@RequestBody List<Contacts> contacts, HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			int idCandidate = contacts.get(0).getIdCandidate();
			contactsService.deleteAllByIdCandidate(idCandidate);
			contactsService.saveAll(contacts);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 7) {
				stateCandidateProfilesService.updateStep(idCandidate,8);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-candidate-step-8")
	public ResponseEntity<ResponseHttp> updateCandidateStep8 (@RequestBody List<CandidatesSkills> candidatesSkills, HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			int idCandidate = candidatesSkills.get(0).getIdCandidate();
			candidatesSkillsService.deleteAllByIdCandidate(idCandidate);
			candidatesSkillsService.saveAll(candidatesSkills);
			if(stateCandidateProfilesService.getStepByIdCandidate(idCandidate) == 8) {
				stateCandidateProfilesService.updateStep(idCandidate, 9);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-dates-step/{idCandidate}")
	public ResponseEntity<ResponseHttp> getDatesStep (@RequestBody Integer step, @PathVariable("idCandidate") Integer idCandidate, 
														HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			switch(step) {
				case 1:
					responseHttp.setDataSource(candidatesService.findById(idCandidate));
					break;
				case 2:
					if (studentsService.existByIdCandidate(idCandidate) == null) {
						responseHttp.setDataSource(instructionService.findByIdCandidate(idCandidate));
					}
					else {
						InstructionStudent istructionStudent = new InstructionStudent();
						istructionStudent.setInstruction(instructionService.findByIdCandidate(idCandidate));
						istructionStudent.setStudent(studentsService.findByIdCandidate(idCandidate));
						responseHttp.setDataSource(istructionStudent);
					}
					break;
				case 3:
					responseHttp.setDataSource(medicalDiagnosesIcdService.findAllByIdCandidate(idCandidate));
					break;
				case 4:
					responseHttp.setDataSource(candidateDetailsService.findAllByIdCandidate(idCandidate));
					break;
				case 5:
					responseHttp.setDataSource(workActivitiesService.findAllByIdCandidate(idCandidate));
					break;
				case 6:
					responseHttp.setDataSource(guardiansService.findByIdCandidate(idCandidate));
					break;
				case 7:
					responseHttp.setDataSource(contactsService.findAllByIdCandidate(idCandidate));
					break;
				case 8:
					responseHttp.setDataSource(candidatesSkillsService.getAllByIdCandidate(idCandidate));
					break;
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get-skills-checked/{idCandidate}")
	public ResponseEntity<ResponseHttp> allSkillsByJobPositions (HttpServletRequest request, 
																@PathVariable("idCandidate") Integer idCandidate){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(candidatesSkillsService.getAllByIdCandidate(idCandidate));
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}

}