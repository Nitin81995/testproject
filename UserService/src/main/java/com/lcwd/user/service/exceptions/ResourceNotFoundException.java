package com.lcwd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Resouece no tfound ");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
