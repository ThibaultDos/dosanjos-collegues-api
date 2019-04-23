package dev.colleguesapi.exceptions;

public class CollegueInvalideException extends CollegueServiceException {

	private static final long serialVersionUID = 1L;
	private static final String COLLEGUE_INVALIDE = "Matricule inconnu." ;
	
	public CollegueInvalideException() {
		super();
	}
	
	public CollegueInvalideException(String message) {
		super(COLLEGUE_INVALIDE);
	}
}
