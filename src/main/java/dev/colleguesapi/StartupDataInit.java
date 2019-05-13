package dev.colleguesapi;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.colleguesapi.controller.dto.Utilisateur;
import dev.colleguesapi.repos.CollegueRepository;
import dev.colleguesapi.repos.UtilisateurRepository;
import dev.colleguesapi.service.CollegueService;

@Component
public class StartupDataInit {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private CollegueRepository collegueRepo;

	@Autowired
	private CollegueService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		
		
		String photoUrl = "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fdhs.gov%2Fsites%2Fdefault%2Ffiles%2Fimages%2FREAL-ID_icon_public.png&f=1";

		Collegue c0 = new Collegue(UUID.randomUUID().toString(), "Nom", "Prenom, Prenom-Secondaire, Prenom-Tertiaire", null,
				LocalDate.of(1, 1, 1), photoUrl);
		c0.setEmail(mailService.generateEmail(c0));

		Collegue c1 = new Collegue(UUID.randomUUID().toString(), "Duff", "John", null, LocalDate.of(2000, 1, 1),
				"https://www.chiens-de-france.com/photo/chiens/2019_03/chiens-Chihuahua-0ef1eb6f-68f9-c874-3d24-71be21dd436e_min.jpg");
		c1.setEmail(mailService.generateEmail(c1));

		Collegue c2 = new Collegue(UUID.randomUUID().toString(), "Bricot", "Judas", null, LocalDate.of(2000, 3, 30),
				"https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.1rwq_803lgbZQQq4QwZ7pwHaHa%26pid%3DApi&f=1");
		c2.setEmail(mailService.generateEmail(c2));

		Collegue c3 = new Collegue(UUID.randomUUID().toString(), "Dépot", "Bricot", null, LocalDate.of(2000, 2, 29),
				"https://www.chiens-de-france.com/photo/chiens/2019_03/chiens-Chihuahua-d2668068-075a-8084-8dc3-c606d9059132_min.jpg");
		c3.setEmail(mailService.generateEmail(c3));

		Collegue c4 = new Collegue(UUID.randomUUID().toString(), "Fruité", "Saumon, Mustélipon", null,
				LocalDate.of(1993, 3, 20),
				"https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/John_William_Waterhouse_-_Mermaid.JPG/220px-John_William_Waterhouse_-_Mermaid.JPG");
		c4.setEmail(mailService.generateEmail(c4));

		Collegue c5 = new Collegue(UUID.randomUUID().toString(), "Huasca", "Taya", null, LocalDate.of(1997, 6, 24),
				"https://www.chiens-de-france.com/photo/chiens/2018_11/chiens-Chihuahua-97392a00-0de5-6154-4d0d-a04dcdd977ab_min.jpg");
		c5.setEmail(mailService.generateEmail(c5));

		Collegue c6 = new Collegue(UUID.randomUUID().toString(), "Huasca", "Tiny", null, LocalDate.of(1997, 6, 24),
				"https://www.chiens-de-france.com/photo/eleveurs/255/50814/album/a8f6ec0f-fa0f-24d4-b1a8-9086d22b2dad_min.jpg");
		c6.setEmail(mailService.generateEmail(c6));

		Collegue c7 = new Collegue(UUID.randomUUID().toString(), "Fonfec", "Sophie", null, LocalDate.of(1998, 07, 12),
				"https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.meatsandsausages.com%2Fpublic%2Fimages%2Fsausage-recipes%2Fsaucisson-beaufort.jpg&f=1");
		c7.setEmail(mailService.generateEmail(c7));

		Collegue c8 = new Collegue(UUID.randomUUID().toString(), "Landes", "Yves", null, LocalDate.of(1998, 12, 20),
				"https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fcigalemistralavande.c.i.pic.centerblog.net%2Fo7szr9qf.jpg&f=1");
		c8.setEmail(mailService.generateEmail(c8));

		Collegue c9 = new Collegue(UUID.randomUUID().toString(), "J'ai su", "Jean, Luc, Mathieu, Marc", null,
				LocalDate.of(0001, 12, 24),
				"https://proxy.duckduckgo.com/iu/?u=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2Fa%2Fad%2FAfterimage.svg%2F542px-Afterimage.svg.png&f=1");
		c9.setEmail(mailService.generateEmail(c9));

		collegueRepo.saveAll(Arrays.asList(c0, c1, c2, c3, c4, c5, c6, c7, c8, c9));
		
		collegueRepo.findAll().forEach(
				(Collegue collegue) -> utilisateurRepository.save(new Utilisateur(collegue.getEmail(), passwordEncoder.encode("pass1"), Arrays.asList("ROLE_ADMIN", "ROLE_COLLEGUE"))
				));
			
		utilisateurRepository.save(new Utilisateur("admin", passwordEncoder.encode("admin"), Arrays.asList("ROLE_COLLEGUE")));
	}
}
