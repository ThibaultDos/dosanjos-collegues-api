package dev.colleguesapi.service;

import java.util.List;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.Notes;
import dev.colleguesapi.exceptions.CollegueNonTrouveException;
import dev.colleguesapi.exceptions.NoteInvalideException;
import dev.colleguesapi.repos.NotesRepository;

@Service
public class NoteService {

	@Autowired
	NotesRepository notesRepo;
	@Autowired
	CollegueService collegueService;

	public List<String> afficherTousLesCommentaires(String matricule) throws CollegueNonTrouveException {
		List<String> tousLesmatricules = collegueService.afficherTousLesMatricules();
		if (tousLesmatricules.contains(matricule)) {
			return this.notesRepo.findAll().stream().map(Notes::getCommentaire).collect(Collectors.toList());
		} else {
			throw new CollegueNonTrouveException();
		}
	}

	public void ajouterUnCommentaire(String matricule, String commentaire)
			throws NoteInvalideException, CollegueNonTrouveException {

		Collegue collegueAcommenter;

		if (StringUtils.isEmpty(matricule)) {
			throw new CollegueNonTrouveException("I AM ERROR.\nAucun matricule renseigné.");
		} else if (collegueService.afficherTousLesMatricules().contains(matricule)) {
			collegueAcommenter = collegueService.rechercherParMatricule(matricule);
		} else {
			throw new CollegueNonTrouveException("I AM ERROR.\nMatricule inconnu au bataillon.");
		}

		if (StringUtils.isEmpty(commentaire)) {
			throw new NoteInvalideException("I AM ERROR.\nAucun commentaire enregistré");
		} else {
			Notes commentaireAAjouter = new Notes(commentaire, collegueAcommenter);
			collegueAcommenter.getNote().add(commentaireAAjouter);
			notesRepo.save(commentaireAAjouter);
		}
	}

}
