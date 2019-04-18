package dev.colleguesapi.exceptions;

public class CollegueInvalideException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * @param message
	 * @param cause
	 */
	public CollegueInvalideException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * 
	 */
	public CollegueInvalideException() {
		super();
	}
	public CollegueInvalideException(String message) {
	super (message);	
	}
}
