package com.wds.watson.rest.domain;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;

import java.util.List;

public class StartConversationDto {
	private String userName;
	private String conversationId;
	private List<String> greetings;
	private Context context;

	public StartConversationDto() {
	}

	public StartConversationDto(String userName, String conversationId, Context context, List<String> greetings) {
		this.userName = userName;
		this.conversationId = conversationId;
		this.context = context;
		this.greetings = greetings;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String v_userName) {
		userName = v_userName;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<String> getGreetings() {
		return greetings;
	}

	public void setGreetings(List<String> greetings) {
		this.greetings = greetings;
	}
}
