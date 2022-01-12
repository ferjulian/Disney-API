package org.alkemy.disneyapi.exception;

@SuppressWarnings("serial")
public class CharacterNotFoundException extends RuntimeException {

	public CharacterNotFoundException(String message) {
		super(message);

	}
}