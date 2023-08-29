package it.teorema.workabilia.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.teorema.workabilia.mapper.CompanyRegistry;
import it.teorema.workabilia.model.LegalRepresentatives;
import it.teorema.workabilia.model.Referents;
import it.teorema.workabilia.service.AdminService;
import it.teorema.workabilia.service.CandidatesService;
import it.teorema.workabilia.service.ChambersOfCommerceService;
import it.teorema.workabilia.service.CompaniesService;
import it.teorema.workabilia.service.CompartmentsService;
import it.teorema.workabilia.service.LegalRepresentativesService;
import it.teorema.workabilia.service.ReferentsService;
import it.teorema.workabilia.service.StatesCompanyProfilesService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	CompaniesService companiesService;
	@Autowired
	AdminService adminService;
	@Autowired
	CandidatesService candidatesService;
	@Autowired
	StatesCompanyProfilesService statesCompanyProfilesService;
	@Autowired
	ChambersOfCommerceService chambersOfCommerceService;
	@Autowired
	LegalRepresentativesService legalRepresentativesService;
	@Autowired
	ReferentsService referentsService;
	@Autowired
	CompartmentsService compartmentsService;
	
	@Autowired
	SecurityController securityController;

	@PostMapping("/update-company-step-1/{idCompany}")
	public ResponseEntity<ResponseHttp> updateCandidateStep1 (@RequestBody CompanyRegistry companyRegistry,
																@PathVariable("idCompany") Integer idCompany,
									 							HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if (candidatesService.existsByEmailCandidates(companyRegistry.getCompany().getEmail()) != null || 
				companiesService.existByEmail(companyRegistry.getCompany().getEmail(), idCompany) != null ||
				adminService.existByEmail(companyRegistry.getCompany().getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else if (companyRegistry.getChamberOfCommerce() != null){
			if (chambersOfCommerceService.findByIdCompany(idCompany) != null) {
				chambersOfCommerceService.updateCompany(companyRegistry.getChamberOfCommerce(), idCompany);
			}
			else {
				chambersOfCommerceService.save(companyRegistry.getChamberOfCommerce());
			}
		}
		else {
			if (chambersOfCommerceService.findByIdCompany(idCompany) != null) {
				chambersOfCommerceService.deleteByIdCompany(idCompany);
			}
		}
		companiesService.updateCompany(companyRegistry.getCompany(), idCompany);
		if(statesCompanyProfilesService.getStepByIdCompany(idCompany) == 1) {
			statesCompanyProfilesService.updateStep(idCompany, 2);
		}
		responseHttp.setCode("1");
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	
	@PostMapping("/update-company-step-2/{idCompany}")
	public ResponseEntity<ResponseHttp> updateCandidateStep1 (@RequestBody LegalRepresentatives legalRepresentatives,
																@PathVariable("idCompany") Integer idCompany,
									 							HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else { 
			if (legalRepresentativesService.findByIdCompany(legalRepresentatives.getIdCompany()) == null) {
				legalRepresentativesService.save(legalRepresentatives);
			}
			else {
				legalRepresentativesService.updateCompany(legalRepresentatives);
			}
			if(statesCompanyProfilesService.getStepByIdCompany(legalRepresentatives.getIdCompany()) == 2) {
				statesCompanyProfilesService.updateStep(legalRepresentatives.getIdCompany(), 3);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/update-company-step-3/{idCompany}")
	public ResponseEntity<ResponseHttp> updateCandidateStep1 (@RequestBody Referents referents,
									 							HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else { 
			if (referentsService.findByIdCompany(referents.getIdCompany()) == null) {
				referentsService.save(referents);
			}
			else {
				referentsService.updateCompany(referents, referents.getIdCompany());
			}
			if(statesCompanyProfilesService.getStepByIdCompany(referents.getIdCompany()) == 3) {
				statesCompanyProfilesService.updateStep(referents.getIdCompany(), 4);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/get-dates-step/{idCompany}")
	public ResponseEntity<ResponseHttp> getDatesStep (@RequestBody Integer step, @PathVariable("idCompany") Integer idCompany, 
														HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			switch(step) {
				case 1:
					CompanyRegistry companyRegistry = new CompanyRegistry();
					companyRegistry.setCompany(companiesService.findByIdCompany(idCompany));
					companyRegistry.setIdSector(compartmentsService.findByIdCompartment(companyRegistry.getCompany().getIdCompartment()));
					companyRegistry.setChamberOfCommerce(chambersOfCommerceService.findByIdCompany(idCompany));
					responseHttp.setDataSource(companyRegistry);
					break;
				case 2:
					responseHttp.setDataSource(legalRepresentativesService.findByIdCompany(idCompany));
					break;
				case 3:
					responseHttp.setDataSource(referentsService.findByIdCompany(idCompany));
					break;
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
}