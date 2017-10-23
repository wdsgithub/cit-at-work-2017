package com.wds.watson.conversation.utils;

import org.apache.commons.lang3.StringUtils;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.wds.watson.configuration.exception.ConversationInputConfigException;

public class ConversationInput {

	private MessageOptions messageOptions;

	ConversationInput(Builder builder) {
		InputData input = new InputData.Builder(builder.question).build();
		messageOptions = buildMessageOptions(input, builder.workSpaceId, builder.context);
	}
	
	public MessageOptions newQuestion(String question){
		InputData input = new InputData.Builder(question).build();
		messageOptions = buildMessageOptions(input, messageOptions.workspaceId(), messageOptions.context());
		return this.getMessageOptions();
	}
	
	public MessageOptions newQuestion(String question, Context context){
		InputData input = new InputData.Builder(question).build();
		messageOptions = buildMessageOptions(input, messageOptions.workspaceId(), context);
		return this.getMessageOptions();
	}
	
	public MessageOptions getMessageOptions() {
		return messageOptions;
	}
	
	private MessageOptions buildMessageOptions(InputData input, String workSpaceId, Context context){
		
		MessageOptions.Builder builder = new MessageOptions.Builder(workSpaceId).input(input).alternateIntents(true);
		if (context == null) {
			return builder.build();
		} else {
			return builder.context(context).build();
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {

		private String question;
		private String workSpaceId;
		private Context context;

		private Builder() {
		}

		public Builder withQuestion(String question) {
			this.question = question;
			return this;
		}

		public Builder withContext(Context ctx) {
			this.context = ctx;
			return this;
		}

		public Builder withWorkSpaceId(String workspaceId) {
			this.workSpaceId = workspaceId;
			return this;
		}
		
		public ConversationInput build() throws ConversationInputConfigException{
			doNullCheck();
			return new ConversationInput(this);
		}

		private void doNullCheck() throws ConversationInputConfigException{
			if(StringUtils.isEmpty(question) || StringUtils.isEmpty(workSpaceId)){
				throw new ConversationInputConfigException("Question and WorkSpaceID may not be null!");
			}
		}
	}
}
