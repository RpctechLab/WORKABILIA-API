package it.teorema.workabilia.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.teorema.workabilia.mapper.ImportFileAbilita;
import it.teorema.workabilia.mapper.ImportFileSettori;
import it.teorema.workabilia.mapper.Login;
import it.teorema.workabilia.model.Compartments;
import it.teorema.workabilia.model.Sectors;
import it.teorema.workabilia.model.Session;
import it.teorema.workabilia.model.Skills1;
import it.teorema.workabilia.model.Skills2;
import it.teorema.workabilia.model.Skills3;
import it.teorema.workabilia.model.Skills4;
import it.teorema.workabilia.service.AuthService;
import it.teorema.workabilia.service.CandidatesService;
import it.teorema.workabilia.service.CompaniesService;
import it.teorema.workabilia.service.CompartmentsService;
import it.teorema.workabilia.service.ProvinceService;
import it.teorema.workabilia.service.SectorsService;
import it.teorema.workabilia.service.SessionService;
import it.teorema.workabilia.service.Skills1Service;
import it.teorema.workabilia.service.Skills2Service;
import it.teorema.workabilia.service.Skills3Service;
import it.teorema.workabilia.service.Skills4Service;
import it.teorema.workabilia.service.StatesCandidateProfilesService;
import it.teorema.workabilia.service.StatesCompanyProfilesService;
import it.teorema.workabilia.service.TemplateService;
import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.session.LocalSession;
import it.teorema.workabilia.utils.ResponseHttp;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AuthService authService;
	@Autowired
	SessionService sessionService;
	@Autowired
	CandidatesService candidatesService;
	@Autowired
	CompaniesService companiesService;
	@Autowired
	StatesCandidateProfilesService stateCandidateProfilesService;
	@Autowired
	StatesCompanyProfilesService statesCompanyProfilesService;
	@Autowired
	SectorsService sectorsService;
	@Autowired
	CompartmentsService compartmentsService;
	@Autowired
	Skills1Service skills1Service;
	@Autowired
	Skills2Service skills2Service;
	@Autowired
	Skills3Service skills3Service;
	@Autowired
	Skills4Service skills4Service;
	@Autowired
	UserService userService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	TemplateService templateService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseHttp> login(@RequestBody Login login) {
		ResponseHttp responseHttp = new ResponseHttp();
		Integer step = 0;
		Integer state = 0;
		responseHttp.setCode("0");
		Integer idUser = authService.loginAdmin(login);
		if (idUser != null) {
			state = userService.getStateByIdUser(idUser);
			responseHttp.setCode("1");
			responseHttp.setMessage("Admin");
		}
		else {
			idUser = authService.loginCandidate(login);
			if (idUser != null) {
				Integer idCandidate = candidatesService.findIdByEmail(login.getEmail());
				step = stateCandidateProfilesService.getStepByIdCandidate(idCandidate);
				state = userService.getStateByIdUser(idUser);
				responseHttp.setCode("1");
				responseHttp.setMessage("Candidate");
			}
			else {
				idUser = authService.loginCompany(login);
				if (idUser != null) {
					Integer idCompany = companiesService.findIdByEmail(login.getEmail());
					step = statesCompanyProfilesService.getStepByIdCompany(idCompany);
					state = userService.getStateByIdUser(idUser);
					responseHttp.setCode("1");
					responseHttp.setMessage("Company");
				}
				else {
					responseHttp.setCode("0");
				}
			}
		}
		if (idUser != null) {
			LocalSession localSession = new LocalSession();
			
			sessionService.deleteTokensById(idUser);

			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);

			sessionService.deleteAllOldToken(date);
			
			UUID firstCode = UUID.randomUUID();
			UUID secondCode = UUID.randomUUID();

			String token = (firstCode.toString().replaceAll("-", "") + ":" + date.getYear() + date.getMonthValue()
					+ date.getDayOfMonth() + date.getHour() + date.getMinute() + date.getSecond() + ":"
					+ secondCode.toString().replaceAll("-", "")).toString();

			String encryptedToken = Base64.getEncoder().encodeToString(token.getBytes());

			Session session = new Session();
			session.setIdUser(idUser);
			session.setToken(encryptedToken);
			session.setDate(date);
			sessionService.save(session);
			
			localSession.setIdLog(authService.findIdEntityByIdUser(idUser));
			localSession.setToken(encryptedToken);
			localSession.setStep(step);
			localSession.setState(state);
			
			
			responseHttp.setDataSource(localSession);
		}
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/import-file-excel")
	public ResponseEntity<ResponseHttp> importFileExcel (@RequestBody List<ImportFileSettori> importFile) {
		ResponseHttp responseHttp = new ResponseHttp();
		sectorsService.deleteAll();
		compartmentsService.deleteAll();
		for (ImportFileSettori file : importFile) {
			Integer idSector = sectorsService.findByName(file.getSettore());
			if (idSector == null) {
				Sectors sectors = new Sectors();
				sectors.setName(file.getSettore());
				sectorsService.save(sectors);
				idSector = sectorsService.findByName(file.getSettore());
			}
			if (compartmentsService.findByName(file.getComparto()) == null) {
				Compartments compartment = new Compartments();
				compartment.setIdSector(idSector);
				compartment.setName(file.getComparto());
				compartmentsService.save(compartment);
			}
		}
		responseHttp.setCode("1");
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/import-file-abilita")
	public ResponseEntity<ResponseHttp> importFileAbilita (@RequestBody List<ImportFileAbilita> importFile) {
		ResponseHttp responseHttp = new ResponseHttp();
		String codiceAbilita1 = null;
		String codiceAbilita2 = null;
		String codiceAbilita3 = null;
		@SuppressWarnings("unused")
		String nomeAbilita1 = null;
		String nomeAbilita2 = null;
		String nomeAbilita3 = null;
		skills1Service.deleteAll();
		skills2Service.deleteAll();
		skills3Service.deleteAll();
		skills4Service.deleteAll();
		for (ImportFileAbilita file : importFile) {
			System.err.println(file.toString());
			if (skills1Service.findByCode(file.getCodice1()) == null && file.getCodice1() != null) {
				Skills1 skills1 = new Skills1();
				skills1.setCode(file.getCodice1());
				skills1.setName(file.getNome());
				skills1Service.save(skills1);
				codiceAbilita1 = file.getCodice1();
				nomeAbilita1 = file.getNome();
			}
			if (file.getCodice2() != null) {
				String codice2 = "" + file.getCodice2().charAt(1);
				if (skills2Service.findByCode(codice2, file.getNome()) == null) {
					Skills2 skills2 = new Skills2();
					skills2.setIdSkill1(skills1Service.findByCode(codiceAbilita1));
					skills2.setCode(codice2);
					skills2.setName(file.getNome());
					skills2Service.save(skills2);
					codiceAbilita2 = codice2;
					nomeAbilita2 = file.getNome();
				}
			}
			if (file.getCodice3() != null) {
				
				String codice3 = "" + file.getCodice3().charAt(2) + file.getCodice3().charAt(3);
				if (skills3Service.findByCode(codice3, file.getNome()) == null) {
					Skills3 skills3 = new Skills3();
					skills3.setIdSkill2(skills2Service.findByCode(codiceAbilita2, nomeAbilita2));
					skills3.setCode(codice3);
					skills3.setName(file.getNome());
					skills3.setQuestion(file.getDomanda());
					skills3Service.save(skills3);
					codiceAbilita3 = codice3;
					nomeAbilita3 = file.getNome();
				}
			}
			if (file.getCodice4() != null) {
				if (!file.getCodice4().equals("aaaaa")) {
					System.err.println(file.toString());
					String codice4;
					if (file.getCodice4().length() == 5) {
						codice4 = "" + file.getCodice4().charAt(4);
					}
					else {
						codice4 = "" + file.getCodice4().charAt(4) + file.getCodice4().charAt(5);
					}
					if (skills4Service.findByCode(codice4, file.getNome()) == null) {
						Skills4 skills4 = new Skills4();
						skills4.setIdSkill3(skills3Service.findByCode(codiceAbilita3, nomeAbilita3));
						skills4.setCode(codice4);
						skills4.setName(file.getNome());
						skills4.setQuestion(file.getDomanda());
						skills4Service.save(skills4);
					}
				}
			}
		}
		responseHttp.setCode("1");
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/import-template-attivita")
	public ResponseEntity<ResponseHttp> importTemplateAttivita (@RequestBody String file) {
		ResponseHttp responseHttp = new ResponseHttp();
		templateService.updateAttivita(file);
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/import-template-abilita")
	public ResponseEntity<ResponseHttp> importTemplateAbilita (@RequestBody String file) {
		ResponseHttp responseHttp = new ResponseHttp();
		templateService.updateAbilita(file);
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	@PostMapping("/get-last-first-name/{idLog}")
	public ResponseEntity<ResponseHttp> getLastFirstName (@PathVariable("idLog") int idLog,
															@RequestBody String ruolo,
																HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		responseHttp.setCode("0");
		if(ruolo.equals("Admin")) {
			responseHttp.setDataSource(userService.nameFromAdmin(idLog));
			responseHttp.setCode("1");
		}
		if(ruolo.equals("Candidate")) {
			responseHttp.setDataSource(userService.nameFromCandidate(idLog));
			responseHttp.setCode("1");
		}
		if(ruolo.equals("Company")) {
			responseHttp.setDataSource(userService.nameFromCompany(idLog));
			responseHttp.setCode("1");
		}
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
	}
	
	
}











