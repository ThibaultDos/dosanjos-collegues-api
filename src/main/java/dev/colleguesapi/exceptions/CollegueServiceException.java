package dev.colleguesapi.exceptions;

public class CollegueServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String RUTO = "I AM ERROR.";

	public CollegueServiceException() {
		super();
	}

	public CollegueServiceException(String message) {
		super(RUTO);
	}

	public CollegueServiceException(Throwable cause) {
		super(cause);
	}
}
