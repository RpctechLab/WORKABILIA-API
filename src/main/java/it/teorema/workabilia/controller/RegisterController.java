package it.teorema.workabilia.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.teorema.workabilia.WorkabiliaApplication;
import it.teorema.workabilia.mapper.RegisterAdmin;
import it.teorema.workabilia.mapper.RegisterCandidate;
import it.teorema.workabilia.mapper.RegisterCompany;
import it.teorema.workabilia.model.Admin;
import it.teorema.workabilia.model.Auth;
import it.teorema.workabilia.model.CandidateDetails;
import it.teorema.workabilia.model.StatesCandidateProfiles;
import it.teorema.workabilia.model.StatesCompanyProfiles;
import it.teorema.workabilia.model.User;
import it.teorema.workabilia.service.AdminService;
import it.teorema.workabilia.service.AuthService;
import it.teorema.workabilia.service.CandidateDetailsService;
import it.teorema.workabilia.service.CandidatesService;
import it.teorema.workabilia.service.CompaniesService;
import it.teorema.workabilia.service.StatesCandidateProfilesService;
import it.teorema.workabilia.service.StatesCompanyProfilesService;
import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	AuthService authService;
	@Autowired
	CandidatesService candidatesService;
	@Autowired
	CompaniesService companiesService;
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	@Autowired
	StatesCandidateProfilesService stateCandidateProfilesService;
	@Autowired
	StatesCompanyProfilesService statesCompanyProfilesService;
	@Autowired
	CandidateDetailsService candidateDetailsService;
	
	@Autowired
	WorkabiliaApplication workabilia;
	@Autowired
	SecurityController securityController;

	
	@PostMapping("/register-candidate")
	public ResponseEntity<ResponseHttp> registerCandidate(@RequestBody RegisterCandidate register) {
		ResponseHttp responseHttp = new ResponseHttp();
		User newUser = new User();
		Auth newAuth = new Auth();
		List<CandidateDetails> detailsList = new ArrayList<CandidateDetails>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		if (candidatesService.existsByEmail(register.getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else{
			StatesCandidateProfiles newState = new StatesCandidateProfiles();
			candidatesService.insertCandidate(register.getFirstName(), register.getLastName(), register.getEmail());
			int idEntity = candidatesService.findIdByEmail(register.getEmail());
			newState.setIdCandidate(idEntity);
			newState.setStep(1);
			stateCandidateProfilesService.save(newState);
			newUser.setIdRole(2);
			newUser.setIdEntity(idEntity);
			newUser.setState(1);
			userService.save(newUser);
			newAuth.setPassword(register.getPassword());
			newAuth.setIdUser(userService.existByEntityCandidate(idEntity));
			newAuth.setDate(LocalDate.parse(format.format(now), format));
			authService.save(newAuth);
			for(int i=1; i<23; i++) {
				detailsList.add(new CandidateDetails(idEntity, i, "", false));
			}
			candidateDetailsService.saveAll(detailsList);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
	}
	
	@PostMapping("/register-company")
	public ResponseEntity<ResponseHttp> registerCompany(@RequestBody RegisterCompany register) {
		ResponseHttp responseHttp = new ResponseHttp();
		User newUser = new User();
		Auth newAuth = new Auth();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		System.out.println("------------------------------");
		System.err.println(register.getEmail());
		if (companiesService.existByEmail(register.getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else{
			StatesCompanyProfiles statesCompanyProfiles = new StatesCompanyProfiles();
			companiesService.insertCompanies(register.getDenomination(), register.getEmail());
			int idEntity = companiesService.findIdByEmail(register.getEmail());
			newUser.setIdRole(3);
			newUser.setIdEntity(idEntity);
			newUser.setState(1);
			userService.save(newUser);
			newAuth.setPassword(register.getPassword());
			newAuth.setIdUser(userService.existByEntityCompanies(idEntity));
			newAuth.setDate(LocalDate.parse(format.format(now), format));
			authService.save(newAuth);
			statesCompanyProfiles.setIdCompany(idEntity);
			statesCompanyProfiles.setStep(1);
			statesCompanyProfilesService.save(statesCompanyProfiles);
			responseHttp.setCode("2");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
	}
	/*@PostMapping("/register")
	public ResponseEntity<ResponseHttp> register(@RequestBody Register register) {
		ResponseHttp responseHttp = new ResponseHttp();
		User newUser = new User();
		Auth newAuth = new Auth();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		if (candidatesService.existsByEmail(register.getRegistry().getEmail()) != null
				|| companiesService.existByEmail(register.getRegistry().getEmail()) != null
				|| adminService.existByEmail(register.getRegistry().getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if (register.getRegistry().getDenomination().length() == 0) {
			StatesCandidateProfiles newState = new StatesCandidateProfiles();
			candidatesService.insertCandidate(register.getRegistry());
			int idEntity = candidatesService.findIdByEmail(register.getRegistry().getEmail());
			newState.setIdCandidate(idEntity);
			newState.setStep(1);
			stateCandidateProfilesService.save(newState);
			newUser.setIdRole(2);
			newUser.setIdEntity(idEntity);
			userService.save(newUser);
			newAuth.setPassword(register.getPassword());
			newAuth.setIdUser(userService.existByEntityCandidate(idEntity));
			newAuth.setDate(LocalDate.parse(format.format(now), format));
			authService.save(newAuth);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			StatesCompanyProfiles statesCompanyProfiles = new StatesCompanyProfiles();
			companiesService.insertCompanies(register.getRegistry());
			int idEntity = companiesService.findIdByEmail(register.getRegistry().getEmail());
			newUser.setIdRole(3);
			newUser.setIdEntity(idEntity);
			userService.save(newUser);
			newAuth.setPassword(register.getPassword());
			newAuth.setIdUser(userService.existByEntityCompanies(idEntity));
			newAuth.setDate(LocalDate.parse(format.format(now), format));
			authService.save(newAuth);
			statesCompanyProfiles.setIdCompany(idEntity);
			statesCompanyProfiles.setStep(1);
			statesCompanyProfilesService.save(statesCompanyProfiles);
			responseHttp.setCode("2");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}*/

	@PostMapping("/register-admin")
	public ResponseEntity<ResponseHttp> registerAdmin(@RequestBody RegisterAdmin registerAdmin,
														HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if (candidatesService.existsByEmail(registerAdmin.getEmail()) != null
				|| companiesService.existByEmail(registerAdmin.getEmail()) != null
				|| adminService.existByEmail(registerAdmin.getEmail()) != null) {
			responseHttp.setCode("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			adminService.insertAdmin(new Admin(registerAdmin.getFirstName(), 
					registerAdmin.getLastName(), registerAdmin.getEmail()));
			int idEntity = adminService.findIdByEmail(registerAdmin.getEmail());
			userService.save(new User(1, idEntity, 1));
			authService.save(new Auth(userService.existByEntityAdmin(idEntity), 
					registerAdmin.getPassword(), LocalDate.parse(format.format(now), format)));
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);

		}
	}

//	@PostMapping("/forget-password")
//	public ResponseEntity<ResponseHttp> forgetPassword(@RequestBody String email) {
//		ResponseHttp responseHttp = new ResponseHttp();
//		responseHttp.setCode("0");
//		Integer idUser = null;
//		if (candidatesService.existsByEmail(email) != null) {
//			idUser = userService.existByEntityCandidate(candidatesService.findIdByEmail(email));
//		}
//		if (companiesService.existByEmail(email) != null) { // da testare, giusta al 99%
//			idUser = userService.existByEntityCompanies(companiesService.findIdByEmail(email));
//		}
//		if (adminService.existByEmail(email) != null) {
//			idUser = userService.existByEntityAdmin(adminService.findIdByEmail(email));
//		}
//		// email
//		if (idUser != null) {
//			UUID randomUUID = UUID.randomUUID();
//			String password = randomUUID.toString().replaceAll("_", "").replaceAll("-", "");
//		    MessageDigest md;
//			try {
//				md = MessageDigest.getInstance("MD5");
//				md.update(password.getBytes());
//			    byte[] digest = md.digest();
//			    String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
//			    authService.updatePassword(idUser, myHash);
//			    workabilia.sendEmail(email, "Autwork | Recupero Password", 
//			    		"La sua nuova password è: " + password);
//			    responseHttp.setCode("1");
//			} 
//			catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//		}
//		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
//	}

	@PostMapping("/check-old-password/{idIdentity}/{role}")
	public ResponseEntity<ResponseHttp> checkOldPassword(@PathVariable("idIdentity") int idIdentity,
			@PathVariable("role") String role, @RequestBody String password, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {

			int idUser = 0;
			if (role.equals("Admin")) {
				idUser = userService.existByEntityAdmin(idIdentity);
			}else if(role.equals("Candidate")) {
				idUser = userService.existByEntityCandidate(idIdentity);
			}else if(role.equals("Company")) {
				idUser = userService.existByEntityCompanies(idIdentity);
			}
			
			if(authService.getPasswordByIdUser(idUser).equals(password)) {
				responseHttp.setCode("1");
			}
		}

		return new ResponseEntity<>(responseHttp, HttpStatus.OK);

	}
	
	@PostMapping("/update-password/{idIdentity}/{role}")
	public ResponseEntity<ResponseHttp> updatePassword(@PathVariable("idIdentity") int idIdentity,
			@PathVariable("role") String role, @RequestBody String password, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {

			int idUser = 0;
			if (role.equals("Admin")) {
				idUser = userService.existByEntityAdmin(idIdentity);
			}else if(role.equals("Candidate")) {
				idUser = userService.existByEntityCandidate(idIdentity);
			}else if(role.equals("Company")) {
				idUser = userService.existByEntityCompanies(idIdentity);
			}
			
			authService.updatePassword(idUser, password);
			responseHttp.setCode("1");
		}

		return new ResponseEntity<>(responseHttp, HttpStatus.OK);

	}
}
