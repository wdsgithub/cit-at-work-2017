package com.wds.watson.rest.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.wds.watson.configuration.exception.ConversationInputConfigException;
import com.wds.watson.conversation.utils.ConversationInput;
import com.wds.watson.rest.domain.AnswerDto;
import com.wds.watson.rest.domain.QuestionDto;
import com.wds.watson.rest.domain.StartConversationDto;
import com.wds.watson.services.ConversationService;
import com.wds.watson.services.WatsonServiceFactory;

/**
 * Rest-Controller für Service-Zugriff auf Svibo Chatbot
 */
@RestController
@RequestMapping("/api/svibo")
public class SviboRestController {

	private static final Logger LOG = LoggerFactory.getLogger(SviboRestController.class);

	/* WATSON ACCESS */
	private static final String WATSON_USER = "myUser";
	private static final String WATSON_PW = "myPw";
	private static final String WATSON_ENDPOINT = "https://gateway-fra.watsonplatform.net/conversation/api";
	private static final String SVIBO_WORKSPACE_ID = "myWorkSpaceId";

	@RequestMapping("/start")
	public StartConversationDto startConversation(@RequestParam("user") String userId) throws ConversationInputConfigException {

		ConversationService conversation = WatsonServiceFactory.getConversationServiceWithOptionalProxy(WATSON_USER, WATSON_PW, WATSON_ENDPOINT);

		/* Initialer Request um Konversation zu starten */
		ConversationInput ci = ConversationInput.newBuilder()
				.withQuestion("Hallo")
				.withWorkSpaceId(SVIBO_WORKSPACE_ID)
				.build();

		MessageResponse resp = conversation.talkToWatson(ci);

		Context context = resp.getContext();
		String conversationId = context.getConversationId();
		LOG.info("Started Conversation with ConversationId {}", conversationId);

		List<String> greetings = getAnswers(resp);

		return new StartConversationDto(userId, conversationId, context, greetings);
	}


	@RequestMapping(path = "/ask", method = RequestMethod.POST)
	public AnswerDto ask(@RequestBody QuestionDto payload) throws ConversationInputConfigException {
		LOG.info(payload.getUserId() + " asked: " + payload.getQuestion());

		String question = StringUtils.trim(payload.getQuestion());
		Context context = payload.getContext();

		ConversationService conversation = WatsonServiceFactory.getConversationServiceWithOptionalProxy(WATSON_USER, WATSON_PW, WATSON_ENDPOINT);
		ConversationInput ci = ConversationInput.newBuilder()
				.withWorkSpaceId(SVIBO_WORKSPACE_ID)
				.withContext(context)
				.withQuestion(question)
				.build();

		MessageResponse resp = conversation.talkToWatson(ci);
		List<String> answers = getAnswers(resp);

		LOG.info("Oxana answered: " + answers.get(0));

		return new AnswerDto(resp.getContext(), answers);
	}

	@RequestMapping("/status")
	public String getStatus() {
		return "OK";
	}

	/**
	 * Liefert die Antworttexte aus der Response
	 * @param response
	 * @return
	 */
	private List<String> getAnswers(MessageResponse response) {
		if (response.getOutput() != null && response.getOutput().getText() != null && !response.getOutput().getText().isEmpty()) {
			return response.getOutput().getText();
		}

		return null;
	}
}
