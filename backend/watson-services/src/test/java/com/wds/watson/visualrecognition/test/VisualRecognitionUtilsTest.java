package com.wds.watson.visualrecognition.test;

import org.junit.Test;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.conversation.utils.VisualRecognitionUtils;
import com.wds.watson.services.VisualRecognitionService;
import com.wds.watson.services.WatsonServiceFactory;

public class VisualRecognitionUtilsTest {

	@Test
	public void testCreatePropertiesStringForCustomClassifier() throws WatsonConfigurationException{
		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");
		
		Classifiers classifiers = vrs.getAllCustomClassifiers();
		
		System.out.println(VisualRecognitionUtils.createPropertiesStringForCustomClassifier(classifiers));;
	}
}
