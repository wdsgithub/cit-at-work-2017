package com.wds.watson.configuration.exception;

public class ConversationInputConfigException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConversationInputConfigException(String message){
		super(message);
	}
	
	public ConversationInputConfigException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ConversationInputConfigException(Throwable cause){
		super(cause);
	}
}
