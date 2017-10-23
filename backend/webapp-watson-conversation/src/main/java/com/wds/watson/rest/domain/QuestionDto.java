package com.wds.watson.rest.domain;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;

public class QuestionDto {

	private String userId;
	private Context context;
	private String question;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
