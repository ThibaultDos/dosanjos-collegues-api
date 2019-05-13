package dev.colleguesapi.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.colleguesapi.controller.dto.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur);
}