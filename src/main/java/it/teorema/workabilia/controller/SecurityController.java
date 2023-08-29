package it.teorema.workabilia.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.teorema.workabilia.service.SessionService;

@Controller
public class SecurityController {
	@Autowired
	SessionService sessionService;
	
	public boolean checkToken(String token) {
		LocalDateTime sessionDate = sessionService.getData(token);
		if (sessionDate == null)
			return false;
		else {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime data = LocalDateTime.parse(format.format(now), format);
			long diffInSeconds = ChronoUnit.SECONDS.between(sessionDate, data);
			if (diffInSeconds > 3600)
				return false;
			else {
				sessionService.updateData(data, token);
				return true;
			}
		}
	}
}