package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.model.Admin;
import it.teorema.workabilia.model.Candidates;
import it.teorema.workabilia.model.Companies;
import it.teorema.workabilia.model.User;
import jakarta.transaction.Transactional;

public interface UserService extends JpaRepository <User, Integer>{

	@Query("select u.id "
			+ "from User u "
			+ "where u.idEntity = :idEntity and u.idRole = 2 ")
	Integer existByEntityCandidate(int idEntity);
	
	@Query("select u.id "
			+ "from User u "
			+ "where u.idEntity = :idEntity and u.idRole = 3")
	Integer existByEntityCompanies(int idEntity);
	
	@Query("select u.id "
			+"from User u "
			+ "where u.idEntity = :idEntity and u.idRole = 1")
	Integer existByEntityAdmin(int idEntity);
	
	@Query("from Admin a "
			+ "where id= :idLog")
	Admin nameFromAdmin(int idLog);
	
	@Query("from Candidates c "
			+ "where id= :idLog")
	Candidates nameFromCandidate(int idLog);
	
	@Query("from Companies c "
			+ "where id= :idLog")
	Companies nameFromCompany(int idLog);

	@Query("select u.state "
			+ "from User u "
			+ "where id = :idUser")
	Integer getStateByIdUser(Integer idUser);

	
	@Transactional
	@Modifying
	@Query("update User "
			+ "set state = 0 "
			+ "where id = :idUser" )
	void changeStateFalse(Integer idUser);
	
	@Transactional
	@Modifying
	@Query("update User "
			+ "set state = 1 "
			+ "where id = :idUser" )
	void changeStateTrue(Integer idUser);
	
	
	
	
}