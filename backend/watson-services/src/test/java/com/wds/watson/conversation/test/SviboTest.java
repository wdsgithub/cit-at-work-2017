package com.wds.watson.conversation.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.wds.watson.configuration.exception.ConversationInputConfigException;
import com.wds.watson.conversation.utils.ConversationInput;
import com.wds.watson.services.ConversationService;
import com.wds.watson.services.WatsonServiceFactory;

public class SviboTest {

	static final String workspaceId = "3f7ffb01-e2bd-4f32-a6e2-33d9b95495e6";

	@Test
	public void testConversationWithSvibo() throws ConversationInputConfigException {
		
		ConversationService conversation = WatsonServiceFactory.getConversationServiceWithDefaultWdsProxy(
				"d2baaf3e-f586-40d1-b852-85b1bf554948", "SrqMLoaoHola",
				"https://gateway-fra.watsonplatform.net/conversation/api");

		ConversationInput ci = ConversationInput.newBuilder().withQuestion("Hallo").withWorkSpaceId(workspaceId)
				.build();
		
		MessageResponse resp;
		resp = conversation.talkToWatson(ci);
		assertNotNull(resp);
		//System.out.println(resp.toString());
		
		Context c;
		c = resp.getContext();
		ci.newQuestion("Test", c);
		
		resp = conversation.talkToWatson(ci);
		assertNotNull(resp);
		//System.out.println(resp.toString());
		
		c = resp.getContext();
		ci.newQuestion("ja, pl1", c);
		resp = conversation.talkToWatson(ci);
		assertNotNull(resp);
		System.out.println(resp.toString());
	}
}
