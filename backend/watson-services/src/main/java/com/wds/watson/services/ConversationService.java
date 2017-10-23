package com.wds.watson.services;

import java.util.List;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.wds.watson.conversation.utils.ConversationInput;

public interface ConversationService {
	
	public List<String> talkWithWatson(ConversationInput input);
	
	public MessageResponse talkToWatson(ConversationInput input);

}
