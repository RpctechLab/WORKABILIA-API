package it.teorema.workabilia.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.Login;
import it.teorema.workabilia.model.Auth;
import jakarta.transaction.Transactional;

public interface AuthService extends JpaRepository <Auth, Integer> {

	@Query("select us.id "
			+ "from Auth au, User us, Admin ad "
			+ "where au.idUser = us.id and ad.id = us.idEntity "
			+ "and au.password = :#{#login.password} and (ad.email = :#{#login.email}) "
			+ "and us.idRole = 1")
	Integer loginAdmin(Login login);
	
	@Query("select us.id "
			+ "from Auth au, User us, Candidates ca "
			+ "where au.idUser = us.id and ca.id = us.idEntity "
			+ "and au.password = :#{#login.password} and (ca.email = :#{#login.email}) "
			+ "and us.idRole = 2")
	Integer loginCandidate(Login login);

	@Query("select us.id "
			+ "from Auth au, User us, Companies co "
			+ "where au.idUser = us.id and co.id = us.idEntity "
			+ "and au.password = :#{#login.password} and (co.email = :#{#login.email}) "
			+ "and us.idRole = 3")
	Integer loginCompany(Login login);
	
	@Query("select idEntity "
			+ "from User "
			+ "where id = :idUser")
	Integer findIdEntityByIdUser(int idUser);
	
	@Query("select a.password "
			+ "from Auth a "
			+ "where a.idUser = :idUser")
	String getPasswordByIdUser(int idUser);
	
	@Transactional
	@Modifying
	@Query("delete from Auth "
			+ "where idUser = :idUser")
	void deleteByIduser(Integer idUser);
	
	@Modifying
	@Transactional
	@Query("update Auth "
			+ "set password = :password "
			+ "where idUser = :idUser")
	void updatePassword(int idUser,String password);
}