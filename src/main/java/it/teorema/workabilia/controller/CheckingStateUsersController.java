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

import it.teorema.workabilia.service.UserService;
import it.teorema.workabilia.utils.ResponseHttp;

@Controller
@RequestMapping("/check")
public class CheckingStateUsersController {

	@Autowired
	UserService userService;
	@Autowired
	SecurityController securityController;

	@PostMapping("/check-status/{id}")
	public ResponseEntity<ResponseHttp> deleteJobPosition(@PathVariable("id") Integer id, @RequestBody Integer idRuolo,
			HttpServletRequest request) {
		ResponseHttp responseHttp = new ResponseHttp();

		Integer idUser = null;
		if (securityController.checkToken(request.getHeader("App-Key")) == false) {
			responseHttp.setCodeSession("0");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		} else {
			if (idRuolo == 1) {
				idUser = userService.existByEntityAdmin(id);
			}
			if (idRuolo == 2) {
				idUser = userService.existByEntityCandidate(id);
			}
			if (idRuolo == 3) {
				idUser = userService.existByEntityCompanies(id);
			}
			Integer state = userService.getStateByIdUser(idUser);
			if (state == 1 && idUser != null) {
				userService.changeStateFalse(idUser);
			} else if (state == 0 && idUser != null) {
				userService.changeStateTrue(idUser);
			}

			responseHttp.setCode("1");
			return new ResponseEntity<>(responseHttp, HttpStatus.OK);
		}
	}
}
