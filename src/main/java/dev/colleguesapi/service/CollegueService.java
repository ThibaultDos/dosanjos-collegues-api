package dev.colleguesapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.PhotoCollegue;
import dev.colleguesapi.exceptions.CollegueInvalideException;
import dev.colleguesapi.exceptions.CollegueNonTrouveException;
import dev.colleguesapi.exceptions.CollegueServiceException;
import dev.colleguesapi.repos.CollegueRepository;


@Service
public class CollegueService {

	@Autowired
	CollegueRepository collegueRepo;

	public String generateEmail(Collegue c) {

		String prenoms = c.getPrenoms();
		String prenom1 = null;
		if (StringUtils.contains(c.getPrenoms(), ", ")) {
			String[] prenomsArray = prenoms.split(", ");
			prenom1 = prenomsArray[0];
		} else {
			prenom1 = c.getPrenoms();
		}
		c.setEmail(prenom1 + "." + c.getNom() + "@societe.com");
		return c.getEmail();
	}

	public List<String> afficherTousLesMatricules() {
		return this.collegueRepo.findAll().stream().map(Collegue::getMatricule).collect(Collectors.toList());
	}

	public List<PhotoCollegue> afficherToutesLesPhotos() {
		return this.collegueRepo.findAll().stream()
				.map(collegue -> new PhotoCollegue(collegue.getMatricule(), collegue.getPhotoUrl()))
				.collect(Collectors.toList());
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		return this.collegueRepo.findByNom(nomRecherche);
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		if (this.collegueRepo.findByMatricule(matriculeRecherche) == null) {
			throw new CollegueNonTrouveException();
		}
		return this.collegueRepo.findById(matriculeRecherche).orElseThrow(() -> new CollegueNonTrouveException());
	}
	
	public Collegue rechercherParMail(String emailRecherche) throws CollegueNonTrouveException {
		if (this.collegueRepo.findByEmail(emailRecherche) == null) {
			throw new CollegueNonTrouveException();
		}
		return this.collegueRepo.findByEmail(emailRecherche).orElseThrow(() -> new CollegueNonTrouveException());
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {

		if (StringUtils.isEmpty(collegueAAjouter.getNom())) {
			throw new CollegueInvalideException("I AM ERROR.\nAucun nom enregistré");
		}

		boolean nomValide = collegueAAjouter.getNom().length() >= 2;

		if (StringUtils.isEmpty(collegueAAjouter.getPrenoms())) {
			throw new CollegueInvalideException("I AM ERROR.\nAucun prénom enregistré");
		}
		String prenoms = collegueAAjouter.getPrenoms();
		String[] prenomsArray = prenoms.split(", ");

		boolean prenomsValide = true;
		for (String p : prenomsArray) {
			if (p.length() <= 2) {
				prenomsValide = false;
				break;
			}
		}

		collegueAAjouter.setEmail(generateEmail(collegueAAjouter));
		boolean emailValide = collegueAAjouter.getEmail().length() >= 3
				&& StringUtils.contains(collegueAAjouter.getEmail(), "@");

		if (StringUtils.isEmpty(collegueAAjouter.getPhotoUrl())) {
			throw new CollegueInvalideException("I AM ERROR.\nAucune photo enregistrée");
		}
		boolean photoUrlValide = StringUtils.startsWithIgnoreCase(collegueAAjouter.getPhotoUrl(), "http");

		if (collegueAAjouter.getDateDeNaissance() == null) {
			throw new CollegueInvalideException("I AM ERROR.\nAucune date de naissance enregistrée");
		}
		boolean dateDeNaissanceValide = LocalDate.now().getYear()
				- collegueAAjouter.getDateDeNaissance().getYear() >= 18;

		if (nomValide && prenomsValide && emailValide && dateDeNaissanceValide && photoUrlValide) {
			collegueAAjouter.setMatricule(UUID.randomUUID().toString());
			collegueAAjouter.setEmail(generateEmail(collegueAAjouter));

			collegueRepo.save(collegueAAjouter);
			return collegueAAjouter;
		} else {
			throw new CollegueInvalideException("I AM ERROR.\nUn champs n'est pas correctement renseigné");
		}
	}

	@Transactional
	public Collegue modifierEmail(String matricule, String email) throws CollegueServiceException {
		Collegue updateCollegue = rechercherParMatricule(matricule);
		updateCollegue.setEmail(email);
		return updateCollegue;
	}

	@Transactional
	public Collegue modifierPhotoUrl(String matricule, String PhotoUrl) throws CollegueServiceException {
		Collegue updateCollegue = rechercherParMatricule(matricule);
		updateCollegue.setPhotoUrl(PhotoUrl);
		return updateCollegue;
	}
}