package dev.colleguesapi.exceptions;

public class NoteInvalideException extends NoteServiceException {

	private static final long serialVersionUID = 1L;

	private static final String NOTE_INVALIDE = "Note invalide.";

	public NoteInvalideException() {
		super();
	}

	public NoteInvalideException(String message) {
		super(NOTE_INVALIDE);
	}

}
