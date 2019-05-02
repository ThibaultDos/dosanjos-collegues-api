package dev.colleguesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.exceptions.CollegueInvalideException;
import dev.colleguesapi.exceptions.CollegueNonTrouveException;
import dev.colleguesapi.service.CollegueService;

@RestController // Ici cette classe va répondre aux requêtes "/collegues"
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {

	@Autowired
	private CollegueService collegueService;
	
	
	@GetMapping
	@RequestMapping("/matricules")
	public List<String> listerTousLesMatricules(){
		
		List<String> listeDeTousLesMatricules = collegueService.afficherTousLesMatricules();
		return listeDeTousLesMatricules;
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

	@GetMapping
//	récupère le contenu de la variable matricule
	@RequestMapping("/{matricule}")
	public ResponseEntity<Collegue> infosCollegue(@PathVariable String matricule) throws CollegueNonTrouveException {

		Collegue infosCollegue = collegueService.rechercherParMatricule(matricule);
		return ResponseEntity.status(HttpStatus.OK).body(infosCollegue);
	}

	@ExceptionHandler(value = { CollegueNonTrouveException.class })
	public ResponseEntity<String> reponseMatriculeException() {
		return ResponseEntity.status(404).body("Collègue non trouvé.");
	}

	@PostMapping
	@RequestMapping("/nouveau")
	public Collegue ajouterUnCollegue(@RequestBody Collegue nouveauCollegue) throws CollegueInvalideException {
		nouveauCollegue = collegueService.ajouterUnCollegue(nouveauCollegue);
		return nouveauCollegue;
	}	
}
