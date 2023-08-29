package it.teorema.workabilia.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.teorema.workabilia.mapper.CandidacyListAdmin;
import it.teorema.workabilia.mapper.CandidatesListAdmin;
import it.teorema.workabilia.mapper.CandidatiesListCompany;
import it.teorema.workabilia.mapper.CompaniesListAdmin;
import it.teorema.workabilia.model.Candidates;
import it.teorema.workabilia.model.CandidatesPositionsCandidancy;
import it.teorema.workabilia.service.AdminService;
import it.teorema.workabilia.service.AssignmentsService;
import it.teorema.workabilia.service.CandidateInfoCodesService;
import it.teorema.workabilia.service.CandidatesPositionsCandidancyService;
import it.teorema.workabilia.service.CandidatesService;
import it.teorema.workabilia.service.CompaniesService;
import it.teorema.workabilia.service.CompartmentsService;
import it.teorema.workabilia.service.JobPositionsService;
import it.teorema.workabilia.service.LegalFormsService;
import it.teorema.workabilia.service.PercentagesFilterService;
import it.teorema.workabilia.service.ProvinceService;
import it.teorema.workabilia.service.QualificationService;
import it.teorema.workabilia.service.RolesService;
import it.teorema.workabilia.service.SectorsService;
import it.teorema.workabilia.service.Skills1Service;
import it.teorema.workabilia.service.Skills2Service;
import it.teorema.workabilia.service.Skills3Service;
import it.teorema.workabilia.service.Skills4Service;
import it.teorema.workabilia.service.SocialNetworkService;
import it.teorema.workabilia.service.StatesPositionsService;
import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/lists")
public class GetListsController {
	@Autowired
	CandidatesService candidatesService;
	@Autowired
	CandidatesPositionsCandidancyService candidatesPositionsCandidancyService;
	@Autowired
	QualificationService qualificationService;
	@Autowired
	CandidateInfoCodesService candidateInfoCodesService;
	@Autowired
	SectorsService sectorsService;
	@Autowired
	CompartmentsService compartmentsService;
	@Autowired
	AssignmentsService assignmentsService;
	@Autowired
	RolesService rolesService;
	@Autowired
	LegalFormsService legalFormsService;
	@Autowired
	SocialNetworkService socialNetworkService;
	@Autowired
	StatesPositionsService statesPositionsService;
	@Autowired
	CompaniesService companiesService;
	@Autowired
	Skills1Service skills1Service;
	@Autowired
	Skills2Service skills2Service;
	@Autowired
	Skills3Service skills3Service;
	@Autowired
	Skills4Service skills4Service;
	@Autowired
	PercentagesFilterService percentagesFilter;
	@Autowired
	JobPositionsService jobPositionsService;
	@Autowired
	AdminService adminService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	UserService userService;
	@Autowired
	SecurityController securityController;
	

	@GetMapping("/all-qualifications")
	public ResponseEntity<ResponseHttp> allQualifications(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(qualificationService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-percentages")
	public ResponseEntity<ResponseHttp> allPercentages(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(percentagesFilter.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-candidates-info-codes")
	public ResponseEntity<ResponseHttp> allCandidatesInfoCodes(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(candidateInfoCodesService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-province")
	public ResponseEntity<ResponseHttp> allProvince(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(provinceService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-sectors")
	public ResponseEntity<ResponseHttp> allSectors(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(sectorsService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-compartments")
	public ResponseEntity<ResponseHttp> allCompartments(HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(compartmentsService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-compartments-by-sectors/{idSector}")
	public ResponseEntity<ResponseHttp> allCompartmentsBySectors(@PathVariable("idSector") int idSector,
																	HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(compartmentsService.findByIdSector(idSector));
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-compartments-by-sectors-name/{sectorName}")
	public ResponseEntity<ResponseHttp> allCompartmentsBySectors(@PathVariable("sectorName") String sectorName,
																	HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(compartmentsService.findByIdSector(sectorsService.findByName(sectorName)));
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/all-assignments")
	public ResponseEntity<ResponseHttp> allAssignments(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(assignmentsService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-role")
	public ResponseEntity<ResponseHttp> allRole(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(rolesService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	@GetMapping("/all-legal-forms")
	public ResponseEntity<ResponseHttp> allLegalForms(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(legalFormsService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-social-network")
	public ResponseEntity<ResponseHttp> allSocialNeatwork(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(socialNetworkService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	@GetMapping("/all-states-positions")
	public ResponseEntity<ResponseHttp> allStatesPositions(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(statesPositionsService.findAllExeptClose());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-companies")
	public ResponseEntity<ResponseHttp> allCompanies(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(companiesService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-skills")
	public ResponseEntity<ResponseHttp> allSkills(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			responseHttp.setCode("1");
			/*responseHttp.setDataSource(new ListOfSkills(skills1Service.findAll(), skills2Service.findAll(), 
														skills3Service.findAll(), skills4Service.findAll()));*/
			responseHttp.setDataSource(skills4Service.getAllSkills());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get-all-job-positions")
	public ResponseEntity<ResponseHttp> getAllJobPositions (HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(jobPositionsService.findAll());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-candidates-admin")
	public ResponseEntity<ResponseHttp> allCandidatesAdmin (HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(candidatesPositionsCandidancyService.findNCandidacy());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-candidacy-admin")
	public ResponseEntity<ResponseHttp> allCandidacyAdmin (HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			List<CandidacyListAdmin> candidacyListAdmin = new ArrayList<CandidacyListAdmin>();
			List<CandidatesPositionsCandidancy> listCpcs = candidatesPositionsCandidancyService.findAll();
			for (CandidatesPositionsCandidancy cpcs : listCpcs) {
				List<CandidacyListAdmin> candidacyApp = 
						candidatesPositionsCandidancyService.getCandidacyList(cpcs.getIdPositioning(), cpcs.getIdCandidate());
				for (CandidacyListAdmin app : candidacyApp) {
					candidacyListAdmin.add(app);
				}
			}
			responseHttp.setDataSource(candidacyListAdmin);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-companies-admin")
	public ResponseEntity<ResponseHttp> allCompanyAdmin (HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(adminService.getAllCompaniesAdmin());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/all-candidates-company/{idEntity}")
	public ResponseEntity<ResponseHttp> allCandidatesCompany (@PathVariable("idEntity") Integer idEntity,
																	HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		Integer idUser = userService.existByEntityCompanies(idEntity);
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			List<CandidatiesListCompany> candidatiesListCompany = new ArrayList<CandidatiesListCompany>();
			List<CandidatesPositionsCandidancy> candidacyList = candidatesPositionsCandidancyService.findAll();
			for (CandidatesPositionsCandidancy cpc : candidacyList) {
				CandidatiesListCompany clp = candidatesPositionsCandidancyService.getCandidatiesListCompany(
						cpc.getIdCandidate(), cpc.getIdPositioning(),idUser);
				if(clp.getId() != null) {
					candidatiesListCompany.add(clp);
				}
			}
			responseHttp.setDataSource(candidatiesListCompany);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/all-admins-list")
	public ResponseEntity<ResponseHttp> allAdmins (HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			responseHttp.setCode("1");
			System.err.println();
			responseHttp.setDataSource(adminService.getListAdmin());
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
}




