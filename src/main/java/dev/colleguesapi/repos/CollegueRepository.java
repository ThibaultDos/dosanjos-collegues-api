package dev.colleguesapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String>{
	
	List<Collegue> findByNom(String nom);
	List<Collegue> findByMatricule(String matricule);		

}
