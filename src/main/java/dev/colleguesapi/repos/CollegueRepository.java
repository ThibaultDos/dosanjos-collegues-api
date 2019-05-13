package dev.colleguesapi.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

	List<Collegue> findByNom(String nom);

	Optional <Collegue> findByMatricule(String matricule);
	
	Optional <Collegue> findByEmail(String email);
}
