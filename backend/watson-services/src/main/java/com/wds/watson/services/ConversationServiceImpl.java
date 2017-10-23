package com.wds.watson.services;

import java.net.Proxy;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.wds.watson.configuration.CustomProxyConfiguration;
import com.wds.watson.conversation.utils.ConversationInput;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;

class ConversationServiceImpl implements ConversationService {

	private static final String DEFAULT_EP = "https://gateway-fra.watsonplatform.net/conversation/api";
	private Conversation watsonConversation;

	@Override
	public List<String> talkWithWatson(ConversationInput input) {
		MessageResponse response = watsonConversation.message(input.getMessageOptions()).execute();
		return response.getOutput().getText();
	}

	@Override
	public MessageResponse talkToWatson(ConversationInput input) {
		return watsonConversation.message(input.getMessageOptions()).execute();
	}

	void initialize(String username, String password, String endpoint) {
		watsonConversation = new Conversation(Conversation.VERSION_DATE_2017_05_26, username, password);
		watsonConversation.setEndPoint(resolveEP(endpoint));
	}

	void initializeWithProxy(CustomProxyConfiguration customProxyConfig, String username, String password,
			String endpoint) {
		watsonConversation = new Conversation(Conversation.VERSION_DATE_2017_05_26, username, password) {
			@Override
			protected OkHttpClient configureHttpClient() {
				Authenticator proxyAuthenticator = OkHttpClientProxyUtils.createAuthentification(customProxyConfig);
				Proxy proxy = OkHttpClientProxyUtils.createProxy(customProxyConfig);
				return super.configureHttpClient().newBuilder().proxy(proxy).proxyAuthenticator(proxyAuthenticator)
						.build();
			}
		};
		watsonConversation.setEndPoint(resolveEP(endpoint));
	}

	private String resolveEP(String endpoint) {
		if (StringUtils.isEmpty(endpoint)) {
			return DEFAULT_EP;
		}
		return endpoint;
	}
}
