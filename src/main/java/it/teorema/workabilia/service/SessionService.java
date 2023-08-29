package it.teorema.workabilia.service;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Session;
import jakarta.transaction.Transactional;

public interface SessionService extends JpaRepository <Session, Integer>{
	@Transactional
	@Modifying
	@Query("delete from Session where idUser = :idLoggato")
	void deleteTokensById(Integer idLoggato);

	@Transactional
	@Modifying
	@Query("delete from Session where datediff(:date, date) > 7")
	void deleteAllOldToken(LocalDateTime date);
	
	@Query("select date "
			+ "from Session "
			+ "where token = :token")
	LocalDateTime getData(String token);
	
	@Transactional
	@Modifying
	@Query("update Session set date = :date where token = :token")
	void updateData(LocalDateTime date, String token);	
}