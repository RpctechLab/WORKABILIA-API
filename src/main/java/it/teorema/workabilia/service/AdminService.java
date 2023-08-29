package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.AdminElement;
import it.teorema.workabilia.mapper.CompaniesListAdmin;
import it.teorema.workabilia.model.Admin;
import jakarta.transaction.Transactional;

public interface AdminService extends JpaRepository <Admin, Integer> {

	@Query("from Admin ad "
			+ "where ad.email = :email")
	Admin existByEmail(String email);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO ad_admin (nome, cognome, email) " 
	+ "VALUES (:#{#admin.firstName}, :#{#admin.lastName}, :#{#admin.email})", nativeQuery = true)
	void insertAdmin(Admin admin);
	
	@Query("select id "
			+ "from Admin "
			+ "where email = :email")
	int findIdByEmail(String email);
	
	@Query("SELECT c.id as id, c.denomination as denomination, "
			+ "concat(r.firstName, SPACE(1), r.lastName) as name, "
			+ "r.phone as telephone, r.email as email, u.state as state, "
			+ "s.name as sector "
			+ "from Companies c, Referents r, User u, Compartments co, Sectors s "
			+ "where c.id = r.idCompany AND c.id = u.idEntity AND u.idRole = 3 "
			+ "and c.idCompartment = co.id and co.idSector = s.id")
	List<CompaniesListAdmin> getAllCompaniesAdmin();
	
	@Query("SELECT a.firstName as firstName, a.lastName as lastName, a.email as email, u.state as state, a.id as id "
			+ "FROM User u, Admin a "
			+ "WHERE a.id = u.idEntity AND u.idRole = 1")
	List<AdminElement> getListAdmin();

	
	@Transactional
	@Modifying
	@Query("update Admin "
			+ "set firstName = :#{#admin.firstName}, "
			+ "lastName = :#{#admin.lastName}, "			  
			+ "email = :#{#admin.email} "
			+ "where id = :#{#admin.id}")
	void updateAdmin(Admin admin);

	
	
	
	
	 
	
}

