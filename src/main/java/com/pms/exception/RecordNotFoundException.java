package com.pms.exception;

public class RecordNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public RecordNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "RecordNotFoundException [message=" + message + "]";
	}
	
	

}
