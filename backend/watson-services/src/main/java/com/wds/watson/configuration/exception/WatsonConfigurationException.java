package com.wds.watson.configuration.exception;

public class WatsonConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public WatsonConfigurationException(String message){
		super(message);
	}
	
	public WatsonConfigurationException(String message, Throwable cause){
		super(message, cause);
	}
	
	public WatsonConfigurationException(Throwable cause){
		super(cause);
	}

}
