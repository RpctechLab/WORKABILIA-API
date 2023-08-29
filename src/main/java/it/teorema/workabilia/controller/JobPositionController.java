package it.teorema.workabilia.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

import it.teorema.workabilia.mapper.CandidacyListAdmin;
import it.teorema.workabilia.mapper.FindAllJobPositions;
import it.teorema.workabilia.mapper.ImportJobSkill;
import it.teorema.workabilia.mapper.JobPositionCount;
import it.teorema.workabilia.mapper.JobPositionEdit;
import it.teorema.workabilia.mapper.JobPositionMatch;
import it.teorema.workabilia.mapper.JobPositionsList;
import it.teorema.workabilia.mapper.ResponseImport;
import it.teorema.workabilia.mapper.SummaryActivity;
import it.teorema.workabilia.mapper.SummarySkills;
import it.teorema.workabilia.model.CandidatesPositionsCandidancy;
import it.teorema.workabilia.model.CandidatesPositionsCandidancyClosed;
import it.teorema.workabilia.model.CandidatesPositionsPreference;
import it.teorema.workabilia.model.ClosedJobPositions;
import it.teorema.workabilia.model.JobPositions;
import it.teorema.workabilia.model.PositionsSkills;
import it.teorema.workabilia.service.CandidatesPositionsCandidancyClosedService;
import it.teorema.workabilia.service.CandidatesPositionsCandidancyService;
import it.teorema.workabilia.service.CandidatesPositionsPreferenceService;
import it.teorema.workabilia.service.CompartmentsService;
import it.teorema.workabilia.service.JobPositionsClosedService;
import it.teorema.workabilia.service.JobPositionsService;
import it.teorema.workabilia.service.PositionsSkillsService;
import it.teorema.workabilia.service.ProvinceService;
import it.teorema.workabilia.service.Skills4Service;
import it.teorema.workabilia.service.TemplateService;
import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/job-position")
public class JobPositionController {

	@Autowired
	JobPositionsService jobPositionsService;
	@Autowired
	JobPositionsClosedService jobPositionsClosedService;
	@Autowired
	UserService userService;
	@Autowired
	CompartmentsService compartmentsService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	PositionsSkillsService positionsSkillsService;
	@Autowired
	Skills4Service skills4Service;
	@Autowired
	CandidatesPositionsPreferenceService candidatesPositionsPreferenceService;
	@Autowired
	CandidatesPositionsCandidancyService candidatesPositionsCandidancyService;
	@Autowired
	CandidatesPositionsCandidancyClosedService candidatesPositionsCandidancyClosedService;
	@Autowired
	TemplateService templateService;
	@Autowired
	SecurityController securityController;
	
	@PostMapping("/update-job-position/{idJob}")
	public ResponseEntity<ResponseHttp> updateJobPosition (@RequestBody JobPositionEdit jobPositions, 
															@PathVariable("idJob") Integer idJob,
															HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			jobPositionsService.updateJobPosition(idJob, jobPositions, date);
			responseHttp.setDataSource(jobPositionsService.findLastJobPositionId());
			responseHttp.setCode("1");
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
			List<JobPositionCount> listJobPositionCount = new ArrayList<JobPositionCount>();
			List<FindAllJobPositions> allJobPositions = jobPositionsService.findJobPositionAdmin();
			if(allJobPositions.size() > 0) {
				for (FindAllJobPositions jp : allJobPositions) {
					JobPositionCount jpc = new JobPositionCount(jobPositionsService.findCandidacy(jp.getIdJob()), jp);
					listJobPositionCount.add(jpc);
				}
			}
			responseHttp.setDataSource(listJobPositionCount);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-all-job-positions-name")
	public ResponseEntity<ResponseHttp> getAllJobPositionsName (@RequestBody List<String> codeList, 
													HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			List<SummarySkills> summarySkillsList = new ArrayList<SummarySkills>();
			for (String string : codeList) {
				summarySkillsList.add(jobPositionsService.getAllJobPositionsName(string));
			}
			responseHttp.setCode("1");
			responseHttp.setDataSource(summarySkillsList);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	// POSIZIONI REALI
	@GetMapping("/get-all-real-job-positions-match/{idCandidate}")
	public ResponseEntity<ResponseHttp> getAllRealJobPositionsMatch (HttpServletRequest request, 
																@PathVariable("idCandidate")int idCandidate) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			List<JobPositionMatch> jpmList = new ArrayList<JobPositionMatch>();
			List<FindAllJobPositions> jp = jobPositionsService.findAllRealJobPositionsOpen(idCandidate);
			boolean favourite;
			for (FindAllJobPositions findAllJobPositions : jp) {
				if(jobPositionsService.findFavourite(idCandidate, findAllJobPositions.getIdJob()) != null) {
					favourite = true;				
				}
				else {
					favourite = false;
				}
				JobPositionMatch jpm = new JobPositionMatch(jobPositionsService.findAllMatch(idCandidate, 
						findAllJobPositions), findAllJobPositions, favourite);
				jpmList.add(jpm);
			}

			Collections.sort(jpmList);
			Collections.reverse(jpmList);
			responseHttp.setDataSource(jpmList);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	// POSIZIONI IDEALI
	@GetMapping("/get-all-ideal-job-positions-match/{idCandidate}")
	public ResponseEntity<ResponseHttp> getAllJobPositionsMatch (HttpServletRequest request, 
																@PathVariable("idCandidate")int idCandidate) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			List<JobPositionMatch> jpmList = new ArrayList<JobPositionMatch>();
			List<FindAllJobPositions> jp = jobPositionsService.findAllIdealJobPositionsOpen(idCandidate);
			boolean favourite;
			for (FindAllJobPositions findAllJobPositions : jp) {
				if(jobPositionsService.findFavourite(idCandidate, findAllJobPositions.getIdJob()) != null) {
					favourite = true;				
				}
				else {
					favourite = false;
				}
				JobPositionMatch jpm = new JobPositionMatch(jobPositionsService.findAllMatch(idCandidate, 
						findAllJobPositions), findAllJobPositions, favourite);
				jpmList.add(jpm);
			}
			Collections.sort(jpmList);
			Collections.reverse(jpmList);
			responseHttp.setDataSource(jpmList);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	//METODO MODIFICATO ADMIN
	@GetMapping("/get-all-job-positions-match-admin/{idCandidate}")
	public ResponseEntity<ResponseHttp> getAllJobPositionsMatchAdmin(HttpServletRequest request, 
																@PathVariable("idCandidate")int idCandidate) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			List<JobPositionMatch> jpmList = new ArrayList<JobPositionMatch>();
			List<FindAllJobPositions> jp = jobPositionsService.findAllJobPositionsOpenAdmin(idCandidate);
			System.err.println(jp.size());
			boolean favourite;
			for (FindAllJobPositions findAllJobPositions : jp) {
				if(jobPositionsService.findFavourite(idCandidate, findAllJobPositions.getIdJob()) != null) {
					favourite = true;				
				}
				else {
					favourite = false;
				}
				JobPositionMatch jpm = new JobPositionMatch(jobPositionsService.findAllMatch(idCandidate, 
						findAllJobPositions), findAllJobPositions, favourite);
				jpmList.add(jpm);
			}

			Collections.sort(jpmList);
			Collections.reverse(jpmList);
			responseHttp.setDataSource(jpmList);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	//METODO MODIFICATO COMPANY
	@GetMapping("/get-all-job-positions-match-company/{idCandidate}")
	public ResponseEntity<ResponseHttp> getAllJobPositionsMatchCompany(HttpServletRequest request, 
																@PathVariable("idCandidate")int idCandidate) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			List<JobPositionMatch> jpmList = new ArrayList<JobPositionMatch>();
			List<FindAllJobPositions> jp = jobPositionsService.findAllJobPositionsOpenCompany(idCandidate);
			System.err.println(jp.size());
			boolean favourite;
			for (FindAllJobPositions findAllJobPositions : jp) {
				if(jobPositionsService.findFavourite(idCandidate, findAllJobPositions.getIdJob()) != null) {
					favourite = true;				
				}
				else {
					favourite = false;
				}
				JobPositionMatch jpm = new JobPositionMatch(jobPositionsService.findAllMatch(idCandidate, 
						findAllJobPositions), findAllJobPositions, favourite);
				jpmList.add(jpm);
			}

			Collections.sort(jpmList);
			Collections.reverse(jpmList);
			responseHttp.setDataSource(jpmList);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/get-job-positions-company/{idCompany}")
	public ResponseEntity<ResponseHttp> getJobPositionsCompany (HttpServletRequest request, 
																@PathVariable("idCompany")int idCompany) {
		System.err.println("entrato");
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			int idEntity = userService.existByEntityCompanies(idCompany);
			List<JobPositionCount> listJobPositionCount = new ArrayList<JobPositionCount>();
			List<FindAllJobPositions> allJobPositions = jobPositionsService.findAllJobPositionsCompany(idEntity);
			System.err.println("post 1");
			for (FindAllJobPositions jp : allJobPositions) {
				JobPositionCount jpc = new JobPositionCount(jobPositionsService.findCandidacy(jp.getIdJob()), jp);
				listJobPositionCount.add(jpc);
			}
			System.err.println("post 2");
			responseHttp.setDataSource(listJobPositionCount);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/get-job-position/{idJob}")
	public ResponseEntity<ResponseHttp> getJobPositions (HttpServletRequest request,@PathVariable("idJob")int idJob,
															@RequestBody String page){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			responseHttp.setCode("1");
			if(page.equals("home") || page.equals("my-candidancy")){
				responseHttp.setDataSource(jobPositionsService.findJobPosition(idJob));
			}
			if(page.equals("history")){
				responseHttp.setDataSource(jobPositionsClosedService.findJobPositionHistory(idJob));
			}
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete-job-position")
	public ResponseEntity<ResponseHttp> deleteJobPosition (@RequestBody Integer id, HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if(securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			jobPositionsService.deleteById(id);
			positionsSkillsService.deleteAllByIdPosition(id);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
			
		}
	}
	
	@GetMapping("/all-skills-by-job-position/{idPosition}")
	public ResponseEntity<ResponseHttp> allSkillsByJobPositions (HttpServletRequest request, @PathVariable("idPosition") Integer idPosition){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(positionsSkillsService.allSkillsByJobPositions(idPosition));
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-state-job-position/{idPosition}")
	public ResponseEntity<ResponseHttp> updateStateJobPosition (@RequestBody Integer idState, HttpServletRequest request, 
																@PathVariable("idPosition") Integer idPosition){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			List<CandidatesPositionsCandidancyClosed> cpccList = new ArrayList<CandidatesPositionsCandidancyClosed>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime data = LocalDateTime.parse(format.format(now), format);
			jobPositionsService.updateStateJobPosition(idState, idPosition);
			jobPositionsService.updateLastDate(data, idPosition);
			if(idState == 4) {
				ClosedJobPositions cjp = new ClosedJobPositions(jobPositionsService.findbyId(idPosition));
				jobPositionsClosedService.save(cjp);
				Integer idPositionClosed = jobPositionsClosedService.getLast(cjp.getLastModificationDate());
				
				List<CandidatesPositionsCandidancy> cpcList = 
						candidatesPositionsCandidancyClosedService.findByIdJob(idPosition);
				for (CandidatesPositionsCandidancy cpc : cpcList) {
					CandidatesPositionsCandidancyClosed cpcc = new CandidatesPositionsCandidancyClosed(cpc);
					cpccList.add(cpcc);
				}
				candidatesPositionsCandidancyClosedService.saveAll(cpccList);
				
				/*candidatesPositionsCandidancyClosedService.saveAll(
						candidatesPositionsCandidancyClosedService.findByIdJob(idPosition));*/
				candidatesPositionsCandidancyClosedService.updateIdJob(idPositionClosed, idPosition);
				positionsSkillsService.skillOpenToClose(idPosition, idPositionClosed);
				jobPositionsService.delete(jobPositionsService.findbyId(idPosition));
				candidatesPositionsCandidancyService.deleteAllByIdJob(idPosition);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/add-favourite/{idCandidate}")
	public ResponseEntity<ResponseHttp> addFavourite(HttpServletRequest request, 
			@PathVariable("idCandidate")int idCandidate,
			@RequestBody Integer idPosition){
		
		
		ResponseHttp responseHttp = new ResponseHttp();

		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			CandidatesPositionsPreference preference  = new CandidatesPositionsPreference(idCandidate, idPosition);
			candidatesPositionsPreferenceService.save(preference);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete-favourite-by-id/{idCandidate}")
	public ResponseEntity<ResponseHttp> deleteFavouriteById(HttpServletRequest request, 
			@RequestBody Integer idPosition,
			@PathVariable("idCandidate")int idCandidate){
		
		
		ResponseHttp responseHttp = new ResponseHttp();

		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			candidatesPositionsPreferenceService.deleteFavouriteById(idPosition, idCandidate);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/position-candidate-candidancy/{idCandidate}")
	public ResponseEntity<ResponseHttp> positionCandidancy(HttpServletRequest request,
			@RequestBody Integer idPosition,
			@PathVariable("idCandidate")int idCandidate){
		ResponseHttp responseHttp = new ResponseHttp();

		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}else {
			if(candidatesPositionsCandidancyService.findByIds(idCandidate,idPosition) != null) {
				candidatesPositionsCandidancyService.deleteByIds(idCandidate,idPosition);
				responseHttp.setCode("1");
				return new ResponseEntity<>(responseHttp, HttpStatus.OK);	
			}else {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime data = LocalDateTime.parse(format.format(now), format);
				CandidatesPositionsCandidancy ca = new CandidatesPositionsCandidancy();
				ca.setIdCandidate(idCandidate);
				ca.setIdPositioning(idPosition);
				ca.setCandidacyDate(data);
				candidatesPositionsCandidancyService.save(ca);
				responseHttp.setCode("1");
				return new ResponseEntity<>(responseHttp, HttpStatus.OK);
			}
			
		}	
	}
	
//	@PostMapping("/delete-position-candidate/{idCandidate}")
//	public ResponseEntity<ResponseHttp> deletePositionCandidate(HttpServletRequest request,
//			@RequestBody Integer idPosition,
//			@PathVariable("idCandidate")int idCandidate){
//		ResponseHttp responseHttp = new ResponseHttp();
//
//		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
//			responseHttp.setCodeSession("0");
//			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
//		}else {
//			candidatesPositionsCandidancyService.deleteByIds(idCandidate,idPosition);
//			responseHttp.setCode("1");
//			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
//		}
//	}
	
	@GetMapping("/get-all-job-positions-match-candidancy/{idCandidate}")
    public ResponseEntity<ResponseHttp> getAllJobPositionsMatchCandidancy (HttpServletRequest request, 
                                                                @PathVariable("idCandidate")int idCandidate) {
        ResponseHttp responseHttp = new ResponseHttp();
        if (securityController.checkToken(request.getHeader("App-Key")) == false) {
            responseHttp.setCodeSession("0");
            return new ResponseEntity<>(responseHttp, HttpStatus.OK);
        } 
        else {
            responseHttp.setCode("1");
            List<JobPositionMatch> jpmList = new ArrayList<JobPositionMatch>();
            List<FindAllJobPositions> jp = jobPositionsService.findAllJobPositionsOpenCandidancy();
            System.err.println(jp.size());
            boolean favourite;
            for (FindAllJobPositions findAllJobPositions : jp) {
                if(jobPositionsService.findFavourite(idCandidate, findAllJobPositions.getIdJob()) != null) {
                    favourite = true;
                }
                else {
                    favourite = false;
                }
                if(candidatesPositionsCandidancyService.findByIds(
                        idCandidate, findAllJobPositions.getIdJob()) != null) {
                    JobPositionMatch jpm = new JobPositionMatch(jobPositionsService.findAllMatch(idCandidate, 
                            findAllJobPositions), findAllJobPositions, favourite);
                    jpmList.add(jpm);
                }
            }
            responseHttp.setDataSource(jpmList);
            return new ResponseEntity<>(responseHttp, HttpStatus.OK);
        }
    }
	
	@GetMapping("/get-job-positions-hystoric-admin")
	public ResponseEntity<ResponseHttp> getAllJobPositionsHystoricAdmin(HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(jobPositionsClosedService.findAllJobPositionsHystoricAdmin());
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/get-all-job-positions-hystoric-company/{idCompany}")
	public ResponseEntity<ResponseHttp> getAllJobPositionsHystoricCompany(HttpServletRequest request,
																@PathVariable("idCompany")int idCompany) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			int idEntity = userService.existByEntityCompanies(idCompany);
			responseHttp.setDataSource(jobPositionsClosedService.getAllJobPositionsHystoricCompany(idEntity, idCompany));
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/get-candidacy/{idJob}")
	public ResponseEntity<ResponseHttp> getCandidacy(HttpServletRequest request,
																@PathVariable("idJob")int idJob) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			List<CandidacyListAdmin> candidacyListAdmin = new ArrayList<CandidacyListAdmin>();
			List<Integer> listIdCandidate = candidatesPositionsCandidancyService.getIdCandidateByIdJob(idJob);
			for (Integer idCandidate : listIdCandidate) {
				List<CandidacyListAdmin> candidacyApp = 
						candidatesPositionsCandidancyService.getCandidacyList(idJob, idCandidate);
				for (CandidacyListAdmin app : candidacyApp) {
					candidacyListAdmin.add(app);
				}
			}
			responseHttp.setDataSource(candidacyListAdmin);
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get-template/{string}")
	public ResponseEntity<ResponseHttp> getTemplate (@PathVariable String string, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			responseHttp.setCode("1");
			responseHttp.setDataSource(templateService.findByName(string));
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/set-step-2/{role}")
	public ResponseEntity<ResponseHttp> setStep2 (@RequestBody JobPositions jobPosition, 
													@PathVariable("role") String role,
													HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else if(jobPositionsService.findCode(jobPosition.getCode()) != null) {
			responseHttp.setCode("0");
			responseHttp.setMessage("Codice già presente a sistema");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
			}
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			jobPosition.setDateEntered(date);
			jobPosition.setLastModificationDate(date);
			//if(userService.existByEntityAdmin(jobPosition.getIdCreatorUser()) != null) {
			if(jobPositionsService.findByCode(jobPosition.getCode()) == null) {
				if(role.equals("Admin")) {
					jobPosition.setIdCreatorUser(userService.existByEntityAdmin(jobPosition.getIdCreatorUser()));
				}
				//if(userService.existByEntityCompanies(jobPosition.getIdCreatorUser()) != null) {
				if(role.equals("Company")) {
					jobPosition.setIdCreatorUser(userService.existByEntityCompanies(jobPosition.getIdCreatorUser()));
				}
			}
			jobPositionsService.save(jobPosition);
			responseHttp.setDataSource(jobPositionsService.findLastJobPositionId());
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/set-step-3")
	public ResponseEntity<ResponseHttp> setStep3 (@RequestBody List<PositionsSkills> positionSkill, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			if(positionSkill.size() > 0) {
				positionsSkillsService.deleteAllByIdPosition(positionSkill.get(0).getIdPositions());
				positionsSkillsService.saveAll(positionSkill);
				jobPositionsService.updateStatusJobPosition(positionSkill.get(0).getIdPositions(), date);
			}
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/import-file-step-2-admin/{idCreator}")
	public ResponseEntity<ResponseHttp> importFileAdmin (@RequestBody List<JobPositionsList> jsonPositionsList, 
													@PathVariable("idCreator") int idCreator, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			List<SummaryActivity> listSummaryActivity = new ArrayList<SummaryActivity>();
			List<String> codes = new ArrayList<String>();
			Integer idUser = 0;
			String error = "";
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			if(userService.existByEntityAdmin(idCreator) != null) {
				idUser = userService.existByEntityAdmin(idCreator);
			}
			/*
			for (JobPositionsList jobPosition : jsonPositionsList) {
				codes.add(jobPosition.getCodice());
			}*/
			for(int i = 0; i < jsonPositionsList.size(); i++) {
				JobPositions jobPosition = new JobPositions(idUser, 1, compartmentsService.findByName(jsonPositionsList.get(i).getComparto()),
								jsonPositionsList.get(i).getCodice(), jsonPositionsList.get(i).getMansione(), 
								jsonPositionsList.get(i).getNome(), jsonPositionsList.get(i).getDescrizione(), 
								jsonPositionsList.get(i).getAttivita(), 
								provinceService.getIdByName(jsonPositionsList.get(i).getProvincia()),
								date, date);
				if(jobPositionsService.findCode(jsonPositionsList.get(i).getCodice()) != null) {
					error = "Codice già inserito a sistema";
				}
				if(jobPosition.getIdCompartment() == null || jobPosition.getCode() == null || jobPosition.getCode() == " "
						|| jobPosition.getTask() == null || jobPosition.getTask() == " " || jobPosition.getName() == null 
						|| jobPosition.getName() == " " || jobPosition.getDescription() == null 
						|| jobPosition.getDescription() == " " || jobPosition.getActivity() == null 
						|| jobPosition.getActivity() == " ") {
					if(error.equals("")) {
						error = "Errore nei dati della posizione lavorativa";
					}
					else {
						error += " ed errore nei dati della posizione lavorativa";
					}
				}
				if(!codes.contains(jobPosition.getCode())) {
					listSummaryActivity.add(new SummaryActivity(jobPosition, error));
					error = "";
				}
				else {
					error = "Codice duplicato nel file excel";
					listSummaryActivity.add(new SummaryActivity(jobPosition, error));
					error = "";
				}
				codes.add(jobPosition.getCode());
			}
			responseHttp.setCode("1");
			responseHttp.setDataSource(listSummaryActivity);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/import-file-step-2-company/{idCreator}")
	public ResponseEntity<ResponseHttp> importFileCompany (@RequestBody List<JobPositionsList> jsonPositionsList, 
													@PathVariable("idCreator") int idCreator, HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			List<SummaryActivity> listSummaryActivity = new ArrayList<SummaryActivity>();
			List<String> codes = new ArrayList<String>();
			Integer idUser = 0;
			String error = "";
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			if(userService.existByEntityCompanies(idCreator) != null) {
				idUser = userService.existByEntityCompanies(idCreator);
			}
			/*
			for (JobPositionsList jobPosition : jsonPositionsList) {
				codes.add(jobPosition.getCodice());
			}*/
			for(int i = 0; i < jsonPositionsList.size(); i++) {
				JobPositions jobPosition = new JobPositions(idUser, 1, compartmentsService.findByName(jsonPositionsList.get(i).getComparto()),
								jsonPositionsList.get(i).getCodice(), jsonPositionsList.get(i).getMansione(), 
								jsonPositionsList.get(i).getNome(), jsonPositionsList.get(i).getDescrizione(), 
								jsonPositionsList.get(i).getAttivita(), 
								provinceService.getIdByName(jsonPositionsList.get(i).getProvincia()),
								date, date);
				if(jobPositionsService.findCode(jsonPositionsList.get(i).getCodice()) != null) {
					error = "Codice già inserito a sistema";
				}
				if(jobPosition.getIdCompartment() == null || jobPosition.getCode() == null || jobPosition.getCode() == " "
						|| jobPosition.getTask() == null || jobPosition.getTask() == " " || jobPosition.getName() == null 
						|| jobPosition.getName() == " " || jobPosition.getDescription() == null 
						|| jobPosition.getDescription() == " " || jobPosition.getActivity() == null 
						|| jobPosition.getActivity() == " ") {
					if(error.equals("")) {
						error = "Errore nei dati della posizione lavorativa";
					}
					else {
						error += " ed errore nei dati della posizione lavorativa";
					}
				}
				if(!codes.contains(jobPosition.getCode())) {
					listSummaryActivity.add(new SummaryActivity(jobPosition, error));
					error = "";
				}
				else {
					error = "Codice duplicato nel file excel";
					listSummaryActivity.add(new SummaryActivity(jobPosition, error));
					error = "";
				}
				codes.add(jobPosition.getCode());
			}
			responseHttp.setCode("1");
			responseHttp.setDataSource(listSummaryActivity);
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/save-step-2")
	public ResponseEntity<ResponseHttp> saveStep2 (@RequestBody List<JobPositions> jobPositionsList, 
													HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			jobPositionsService.saveAll(jobPositionsList);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/import-file-step-3")
	public ResponseEntity<ResponseHttp> importFileStep3 (@RequestBody List<ImportJobSkill> importJobSkill, HttpServletRequest request) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ResponseHttp responseHttp = new ResponseHttp();
		List<Integer> arrayIdPositions = new ArrayList<Integer>();
		List<PositionsSkills> listAllSkill = new ArrayList<PositionsSkills>();
		List<PositionsSkills> listSkill4 = new ArrayList<PositionsSkills>();
		List<String> listCodiciErrati = new ArrayList<String>();
		List<String> listCodiciCorretti = new ArrayList<String>();
		List<SummarySkills> listSummarySkills = new ArrayList<SummarySkills>();
		List<String> Skills4Code = skills4Service.getSkill4Code();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
		ImportJobSkill firstPosizionSkill = importJobSkill.get(0);
		for (int c = 1; c < importJobSkill.size(); c++) {  //colonna
			ImportJobSkill jobSkill = importJobSkill.get(c);
			for (int d = 1; d < 16; d++) {  //riga
				Method method = jobSkill.getClass().getMethod("getPosizione" + d);
				String posizione = (String) method.invoke(jobSkill);
				if (posizione != null) {  //controllo che la cella non sia null
					if (posizione.indexOf("0") > -1) {  //controllo che la cella sia checkata
						PositionsSkills positionSkill = new PositionsSkills();
						String getCodeMethod = firstPosizionSkill.getClass().
								getMethod("getPosizione" + d).invoke(firstPosizionSkill).
									toString().replace(" ", "");  //controllo sulla colonna
						
						// trovare l'id della posizione dal codice
						if(jobPositionsService.findByCode(getCodeMethod) != null) {
							positionSkill.setIdPositions(jobPositionsService.findByCode(getCodeMethod));
						}
						//controllo se il check è sul terzo livello
						if (jobSkill.getCodice3() != null && (jobSkill.getCodice3().charAt(0) == 'b' || jobSkill.getCodice3().charAt(0) == 'd')) {
							positionSkill.setSkillCode(jobSkill.getCodice3());
						}
						//controllo se il check è sul quarto livello
						if (jobSkill.getCodice4() != null && (jobSkill.getCodice4().charAt(0) == 'b' || jobSkill.getCodice4().charAt(0) == 'd')) {
							positionSkill.setSkillCode(jobSkill.getCodice4());
						}
						
						if(positionSkill.getSkillCode() != null && positionSkill.getIdPositions() != null) {
							listAllSkill.add(positionSkill);
							if(!listCodiciCorretti.contains(getCodeMethod)) {
								listCodiciCorretti.add(getCodeMethod);
								if(positionsSkillsService.existJobSkills(positionSkill.getIdPositions()) == null)
									listSummarySkills.add(new SummarySkills(getCodeMethod, 
										jobPositionsService.findNameByJobId(positionSkill.getIdPositions()), ""));
								else {
									listSummarySkills.add(new SummarySkills(getCodeMethod, 
										jobPositionsService.findNameByJobId(positionSkill.getIdPositions()),
											"Abilità già presenti a sistema per questa posizione"));
								}
							}
							//salvo gli id delle posizioni lavorative per poi aggiornarne lo stato
							if (!arrayIdPositions.contains(positionSkill.getIdPositions())) {
								arrayIdPositions.add(positionSkill.getIdPositions());
							}
						}
						else {
							if(!listCodiciErrati.contains(getCodeMethod)) {
								listCodiciErrati.add(getCodeMethod);
								listSummarySkills.add(new SummarySkills(getCodeMethod, 
										"-", "Codice non presente a sistema"));
							}
						}
					}
				}
			}
		}
		for (PositionsSkills job : listAllSkill) {
			if(job.getSkillCode().length() == 4) {
				for (String s : Skills4Code) {
					if(s.substring(0, 4).equals(job.getSkillCode())) {
            			PositionsSkills ps = new PositionsSkills(job.getIdPositions(), s, null, null);
            			listSkill4.add(ps);
            		}
				}
			}
		}
		listAllSkill.addAll(listSkill4);
		ResponseImport responseImport = new ResponseImport(listAllSkill, listSummarySkills, arrayIdPositions);
		responseHttp.setCode("1");
		responseHttp.setDataSource(responseImport);
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/save-step-3")
	public ResponseEntity<ResponseHttp> saveStep3 (@RequestBody ResponseImport responseImport, 
													HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} 
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.parse(format.format(LocalDateTime.now()), format);
			for (Integer i : responseImport.getArrayIdPositions()) {
				jobPositionsService.updateStatusJobPosition(i, date);
			}
			positionsSkillsService.saveAll(responseImport.getListpositionSkill());
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
}























