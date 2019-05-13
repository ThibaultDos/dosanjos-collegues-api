package dev.colleguesapi.exceptions;

public class NoteServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoteServiceException() {
		super();
	}

	public NoteServiceException(String message) {
		super(message);
	}

	public NoteServiceException(Throwable cause) {
		super(cause);
	}

}
