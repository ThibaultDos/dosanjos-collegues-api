package dev.colleguesapi.exceptions;

public class CollegueNonTrouveException extends CollegueServiceException {
	
	private static final long serialVersionUID = 1L;
	private static final String MATRICULE_EXCEPTION = "Matricule inconnu." ;
	
	public CollegueNonTrouveException() {
	super ();	
	}

	/**
	 * @param message
	 */
	public CollegueNonTrouveException(String message) {
		super(MATRICULE_EXCEPTION);
	}		
}
