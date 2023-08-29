package it.teorema.workabilia.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.teorema.workabilia.mapper.AllSkills;
import it.teorema.workabilia.model.Skills4;

public interface Skills4Service extends JpaRepository <Skills4, Integer> {
	@Query("SELECT id "
			+ "from Skills4 "
			+ "where code = :codice4 and name = :nome")
	Integer findByCode(String codice4, String nome);
	
	@Query("SELECT CONCAT(s1.code, s2.code, s3.code, s4.code) as skillCode "
			+ "from Skills1 as s1, Skills2 as s2, Skills3 as s3, Skills4 as s4 "
			+ "where s4.idSkill3 = s3.id and s3.idSkill2 = s2.id and s2.idSkill1 = s1.id")
	List<String> getSkill4Code();
	
	@Query("SELECT a1.code AS code1, a1.name AS name1, concat(a1.code, a2.code) AS code2, a2.name AS name2, "
            + "concat(a1.code, a2.code, a3.code) AS code3, a3.name AS name3, a3.question AS question3, "
            + "concat(a1.code, a2.code, a3.code, a4.code) AS code4, a4.name AS name4, a4.question AS question4 "
            + "FROM Skills1 as a1 "
            + "JOIN Skills2 as a2 on a1.id = a2.idSkill1 "
            + "JOIN Skills3 as a3 on a2.id = a3.idSkill2 "
            + "LEFT JOIN Skills4 as a4 on a3.id = a4.idSkill3 "
            + "order by a1.code, a2.code, a3.code, a4.code")
	List<AllSkills> getAllSkills();
}