package com.wds.visualrecognition.training;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.conversation.utils.WatsonTrainingsSet;
import com.wds.watson.services.VisualRecognitionService;
import com.wds.watson.services.WatsonServiceFactory;

public class VRTraining {
	
	@Test
	public void trainClassifier() throws FileNotFoundException, WatsonConfigurationException{
		WatsonTrainingsSet w1 = new WatsonTrainingsSet("Disney_1879524408", "E:/Watson/Star Wars/Darth Vader.zip", "Darth Vader.zip");
		VisualRecognitionService vrs = WatsonServiceFactory.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");
		vrs.trainCustomClassifier("Disney_1879524408", null, Arrays.asList(w1));
	}
	
	@Test
	public void createClassifier() throws FileNotFoundException, WatsonConfigurationException{
		VisualRecognitionService vrs = WatsonServiceFactory.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");
		
		WatsonTrainingsSet w1 = new WatsonTrainingsSet("Villa", "E:/Watson/Haus/Villa.zip", "Villa.zip");
		WatsonTrainingsSet w2 = new WatsonTrainingsSet("Einfamilienhaus", "E:/Watson/Haus/Einfamilienhaus.zip", "Einfamilienhaus.zip");
		
		Classifier c = vrs.createCustomClassifier("Haus", Arrays.asList(w1,w2), null);
		assertNotNull(c);
		System.out.println(c);
	}

}
