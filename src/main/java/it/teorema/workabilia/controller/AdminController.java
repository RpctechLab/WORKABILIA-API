package it.teorema.workabilia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.teorema.workabilia.model.Admin;
import it.teorema.workabilia.service.AdminService;
import it.teorema.workabilia.service.AuthService;
import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.utils.ResponseHttp;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {	
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	AuthService authService;
	@Autowired
	SecurityController securityController;
	
	
	@GetMapping("/delete-admin/{idDeleteAdmin}")
	public ResponseEntity<ResponseHttp> deleteJobPosition (@PathVariable("idDeleteAdmin") Integer idDeleteAdmin, 
															HttpServletRequest request){
		ResponseHttp responseHttp = new ResponseHttp();
		if(securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
		else {
			adminService.deleteById(idDeleteAdmin);
			Integer idUser = userService.existByEntityAdmin(idDeleteAdmin);
			userService.deleteById(idUser);
			authService.deleteByIduser(idUser);
			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}	
	
	@PostMapping("/update-admin")
	public ResponseEntity<ResponseHttp> updateCandidateStep1 (@RequestBody Admin admin,
									 							HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}		 
		else {
			adminService.updateAdmin(admin);			 
		}
		responseHttp.setCode("1");
		return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}


