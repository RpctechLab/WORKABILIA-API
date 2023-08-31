package it.teorema.workabilia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WorkabiliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkabiliaApplication.class, args);
	}

	@Autowired
	private Email email; //email

	public void sendEmail(String toEmail, String subject, String body) {  //email
		email.sendEmail(toEmail, subject, body);
	}
}
