package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.PhotoCollegue;
import dev.colleguesapi.exceptions.CollegueInvalideException;
import dev.colleguesapi.exceptions.CollegueNonTrouveException;
import dev.colleguesapi.exceptions.CollegueServiceException;
import dev.colleguesapi.service.CollegueService;

@RestController
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {

	@Autowired
	private CollegueService collegueService;

	@GetMapping("/matricules")
	public List<String> listerTousLesMatricules() {
		List<String> listeDeTousLesMatricules = collegueService.afficherTousLesMatricules();
		return listeDeTousLesMatricules;
	}
	
	@GetMapping("/photos")
	public List<PhotoCollegue> listerToutesLesPhotos() {
		List<PhotoCollegue> listeDeToutesLesPhotos = collegueService.afficherToutesLesPhotos();
		return listeDeToutesLesPhotos;
	}
	
	@GetMapping
	public List<String> listerCollegues(@RequestParam("nom") String nomSaisiDansLaRequete) {
		List<Collegue> listeColleguesTrouves = collegueService.rechercherParNom(nomSaisiDansLaRequete);
		List<String> listeMatricules = new ArrayList<>();
		for (Collegue c : listeColleguesTrouves) {
			listeMatricules.add(c.getMatricule());
		}
		return listeMatricules;
	}

	@GetMapping("/{matricule}")
	public ResponseEntity<Collegue> infosCollegue(@PathVariable String matricule) throws CollegueNonTrouveException {
		Collegue infosCollegue = collegueService.rechercherParMatricule(matricule);
		return ResponseEntity.status(HttpStatus.OK).body(infosCollegue);
	}

	@PostMapping
	public ResponseEntity<Collegue> ajouterUnCollegue(@RequestBody Collegue nouveauCollegue) throws CollegueInvalideException {
		nouveauCollegue = collegueService.ajouterUnCollegue(nouveauCollegue);
		return ResponseEntity.status(HttpStatus.OK).body(nouveauCollegue);
	}

	@PatchMapping("/{matricule}")
	public ResponseEntity<Collegue> modifierUnCollegue(@RequestBody Collegue collegueUpdate, @PathVariable String matricule) throws CollegueServiceException {
		Collegue collegueUpdated = collegueService.rechercherParMatricule(matricule);
		
		String newEmail = collegueUpdate.getEmail();
		String newphotoUrl = collegueUpdate.getPhotoUrl();
		
		if (newEmail != null) {
			collegueUpdated = collegueService.modifierEmail(matricule, newEmail);
		}
		
		if (newphotoUrl != null) {
			collegueUpdated = collegueService.modifierPhotoUrl(matricule, newphotoUrl);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(collegueUpdated);
	}

	@ExceptionHandler(value = {CollegueNonTrouveException.class})
	public ResponseEntity<String> reponseMatriculeException() {
		return ResponseEntity.status(404).body("Collègue non trouvé.");
	}


}
