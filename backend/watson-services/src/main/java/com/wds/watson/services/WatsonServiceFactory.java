package com.wds.watson.services;

import org.apache.commons.lang3.StringUtils;

import com.wds.watson.configuration.CustomProxyConfigProvider;
import com.wds.watson.configuration.CustomProxyConfiguration;
import com.wds.watson.configuration.exception.WatsonConfigurationException;

public class WatsonServiceFactory {
	
	private static final String PROXY_ENV_PARAM = "wdsProxy";


	public static ConversationService getConversationService(String username, String password, String endpoint){
		ConversationServiceImpl c = new ConversationServiceImpl();
		c.initialize(username, password, endpoint);
		return c;
	}
	
	public static ConversationService getConversationServiceWithDefaultWdsProxy(String username, String password, String endpoint){
		ConversationServiceImpl c = new ConversationServiceImpl();
		c.initializeWithProxy(CustomProxyConfigProvider.createDefaultWdsProxyConfig(), username, password, endpoint);
		return c;
	}

	public static ConversationService getConversationServiceWithOptionalProxy(String username, String password, String endpoint) {
		return isWdsProxy() ?
				getConversationServiceWithDefaultWdsProxy(username, password, endpoint) :
				getConversationService(username, password, endpoint);
	}
	
	public static ConversationService getConversationServiceWithCustomProxy(CustomProxyConfiguration customProxyConfig, String username, String password, String endpoint){
		ConversationServiceImpl c = new ConversationServiceImpl();
		c.initializeWithProxy(customProxyConfig, username, password, endpoint);
		return c;
	}
	
	public static VisualRecognitionService getVisualRecognitionServiceWithDefaultWdsProxy(String apiKey) throws WatsonConfigurationException{
		if(StringUtils.isEmpty(apiKey)){
			throw new WatsonConfigurationException("ApiKey may not be empty or null!");
		}
		VisualRecognitionServiceImpl vrs = new VisualRecognitionServiceImpl(apiKey);
		vrs.initializeWithProxy(CustomProxyConfigProvider.createDefaultWdsProxyConfig());
		return vrs;
	}
	
    public static VisualRecognitionService getVisualRecognitionService(String apiKey) throws WatsonConfigurationException{
        if(StringUtils.isEmpty(apiKey)){
            throw new WatsonConfigurationException("ApiKey may not be empty or null!");
        }
        VisualRecognitionServiceImpl vrs = new VisualRecognitionServiceImpl(apiKey);
        vrs.initialize();
        return vrs;
    }

	public static VisualRecognitionService getVisualRecognitionServiceWithOptionalProxy(String apiKey) throws WatsonConfigurationException{
		return isWdsProxy() ?
				getVisualRecognitionServiceWithDefaultWdsProxy(apiKey) :
				getVisualRecognitionService(apiKey);
	}

	private static boolean isWdsProxy() {
		return "true".equalsIgnoreCase(System.getenv().get(PROXY_ENV_PARAM));
	}
}
