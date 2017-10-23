package com.wds.watson.rest.domain;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;

import java.util.List;

public class AnswerDto {
	private List<String> answers;
	private Context context;

	public AnswerDto() {
	}

	public AnswerDto(Context context, List<String> answers) {
		this.context = context;
		this.answers = answers;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
