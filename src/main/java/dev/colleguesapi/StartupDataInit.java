package dev.colleguesapi;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.colleguesapi.repos.CollegueRepository;

@Component
public class StartupDataInit {
	
	@Autowired
    private CollegueRepository collegueRepo;
	@Autowired
	
	public Map<String, Collegue> data = new HashMap<>();

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
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
		collegueRepo.saveAll(data.values());

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
