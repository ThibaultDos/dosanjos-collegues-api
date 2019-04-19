package dev.colleguesapi.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import dev.colleguesapi.Collegue;
import dev.colleguesapi.exceptions.CollegueInvalideException;

public class CollegueServiceTest {

	private String photoUrlTest = "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fdhs.gov%2Fsites%2Fdefault%2Ffiles%2Fimages%2FREAL-ID_icon_public.png&f=1";
	private String emailTest = null;
	private Collegue collegueTest;
	private CollegueService collegueService = new CollegueService();

	@Before
	public void setUp() {
		/**
		 * Instancie un nouveau collègue et un nouveau service indépendamment de
		 * chaque test
		 */
		this.collegueTest = new Collegue(null, "NomValide", "PrénomValide, DeuxièmePrénomValide", emailTest, LocalDate.of(1993, 3, 20),
				photoUrlTest);
		collegueTest.setEmail(collegueService.generateEmail(collegueTest));
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_NomInvalide() throws CollegueInvalideException {
		String nom = null;
		collegueTest.setNom(nom);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_NomInvalide2() throws CollegueInvalideException {
		String nom = "";
		collegueTest.setNom(nom);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_NomInvalide3() throws CollegueInvalideException {
		String nom = "X";
		collegueTest.setPrenoms(nom);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	// redéfinit la règle pour ce test
	@Test(expected = CollegueInvalideException.class) 
	// Rend le test non-passant
	public void testAjouterUnCollegueNOK_NomValide() throws CollegueInvalideException {
		String nom = "Untel-Inside";
		collegueTest.setPrenoms(nom);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_PrenomsInvalide() throws CollegueInvalideException {
		String prenoms = null;
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_PrenomsInvalide2() throws CollegueInvalideException {
		String prenoms = "";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_PrenomsInvalide3() throws CollegueInvalideException {
		String prenoms = "X";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_PrenomsInvalide4() throws CollegueInvalideException {
		String prenoms = "X, Pierre";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test passant
	public void testAjouterUnCollegueOK_PrenomsInvalide5() throws CollegueInvalideException {
		String prenoms = "Jean, X";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}
	
	@Test(expected = CollegueInvalideException.class)
	// Rend le test non-passant
	public void testAjouterUnCollegueNOK_PrenomsValide() throws CollegueInvalideException {
		String prenoms = "Jean-Pierre";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

	@Test(expected = CollegueInvalideException.class)
	// Rend le test non-passant
	public void testAjouterUnCollegueNOK_PrenomsValide2() throws CollegueInvalideException {
		String prenoms = "Jean, Pierre";
		collegueTest.setPrenoms(prenoms);
		collegueService.ajouterUnCollegue(collegueTest);
	}

}