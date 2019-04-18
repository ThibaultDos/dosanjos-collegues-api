package dev.colleguesapi.exceptions;

public class CollegueNonTrouveException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String RUTO = "I AM ERROR.\nMatricule inconnu." ;
	
	public CollegueNonTrouveException() {
	super (RUTO);	
	}
}
