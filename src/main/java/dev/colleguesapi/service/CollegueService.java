package dev.colleguesapi.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.exceptions.CollegueInvalideException;
import dev.colleguesapi.exceptions.CollegueNonTrouveException;

@Service
public class CollegueService {

	private static final Logger LOG = LoggerFactory.getLogger(CollegueService.class);

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {

		String photoUrl = "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fdhs.gov%2Fsites%2Fdefault%2Ffiles%2Fimages%2FREAL-ID_icon_public.png&f=1";
		String email = null;

		Collegue c1 = new Collegue(UUID.randomUUID().toString(), "Duff", "John", email, LocalDate.of(2000, 1, 1),
				"https://www.chiens-de-france.com/photo/chiens/2019_03/chiens-Chihuahua-0ef1eb6f-68f9-c874-3d24-71be21dd436e_min.jpg");
		c1.setEmail(generateEmail(c1));

		Collegue c2 = new Collegue(UUID.randomUUID().toString(), "Bricot", "Judas", email, LocalDate.of(2000, 3, 30),
				"https://www.chiens-de-france.com/photo/eleveurs/255/50814/album/a8f6ec0f-fa0f-24d4-b1a8-9086d22b2dad_min.jpg");
		c2.setEmail(generateEmail(c2));

		Collegue c3 = new Collegue(UUID.randomUUID().toString(), "Dépot", "Bricot", email, LocalDate.of(2000, 2, 29),
				"https://www.chiens-de-france.com/photo/chiens/2019_03/chiens-Chihuahua-d2668068-075a-8084-8dc3-c606d9059132_min.jpg");
		c3.setEmail(generateEmail(c3));

		Collegue c4 = new Collegue(UUID.randomUUID().toString(), "Fruité", "Saumon, Mustélipon", email,
				LocalDate.of(1993, 3, 20), photoUrl);
		c4.setEmail(generateEmail(c4));

		Collegue c5 = new Collegue(UUID.randomUUID().toString(), "Huasca", "Taya", email, LocalDate.of(1997, 6, 24),
				"https://www.chiens-de-france.com/photo/chiens/2018_11/chiens-Chihuahua-97392a00-0de5-6154-4d0d-a04dcdd977ab_min.jpg");
		c5.setEmail(generateEmail(c5));

		Collegue c6 = new Collegue(UUID.randomUUID().toString(), "Huasca", "Tiny", email, LocalDate.of(1997, 6, 24),
				photoUrl);
		c6.setEmail(generateEmail(c6));

		data.put(c1.getMatricule(), c1);
		data.put(c2.getMatricule(), c2);
		data.put(c3.getMatricule(), c3);
		data.put(c4.getMatricule(), c4);
		data.put(c5.getMatricule(), c5);
		data.put(c6.getMatricule(), c6);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> collegueParNom = new ArrayList<>();
		for (Collegue c : data.values()) {
			if (c.getNom().equals(nomRecherche)) {
				collegueParNom.add(c);
			} else {

				LOG.error("I AM ERROR.");
				LOG.info("Le nom du collègue ciblé ne correspond pas à {}", nomRecherche);
			}
		}
		return collegueParNom;
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {

		// recherche par clé dans la map et renvoie l'objet trouvé
		Collegue collegueParMatricule = data.get(matriculeRecherche);
		if (collegueParMatricule == null) {
			throw new CollegueNonTrouveException();
		}
		return collegueParMatricule;
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

		if (StringUtils.isEmpty(collegueAAjouter.getEmail())) {
			throw new CollegueInvalideException("I AM ERROR.\nAucun e-mail enregistré");
		}
		// génère une adresse mail à partir des infos du collègue :
		// prenom.nom@societe.com
		collegueAAjouter.setEmail(generateEmail(collegueAAjouter));
		boolean emailValide = collegueAAjouter.getEmail().length() >= 3
				// &&
				// EmailValidator.getInstance().isValid(collegueAAjouter.getEmail());
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
			data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
			return collegueAAjouter;
		} else {
			throw new CollegueInvalideException("I AM ERROR.\nUn champs n'est pas correctement renseigné");
		}
	}

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
		String email = c.getEmail();
		return email;
	}
}