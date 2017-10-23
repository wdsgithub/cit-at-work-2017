package com.wds.watson.visualrecognition.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.services.VisualRecognitionService;
import com.wds.watson.services.WatsonServiceFactory;

public class CustomClassifierTest {

	@Test
	public void getCustomClassifiers() throws WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");

		Classifiers classifiers = vrs.getAllCustomClassifiers();
		assertNotNull(classifiers);
		System.out.println(classifiers.toString());

	}

	@Test
	public void getCustomClassifierDetails() throws WatsonConfigurationException, FileNotFoundException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");
		
		//String clOptions = "{\"classifier_ids\": [\"StarWars_1711751787\"," + "\"Disney_1879524408\"]}";
		String clOptions = "{\"classifier_ids\": [\"StarWars_1711751787\"]}";

		ClassifiedImages c = vrs.classifyWithCustomClassifier(clOptions, "./src/test/resources/darth-vader.jpg");

		assertNotNull(c);
		System.out.println(c.toString());
	}

	@Test
	public void getCustomClassifierDetailsInputStream() throws WatsonConfigurationException, IOException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");

		//String clOptions = "{\"classifier_ids\": [\"StarWars_1711751787\"," + "\"Disney_1879524408\"]}";
		String clOptions = "{\"classifier_ids\": [\"Disney_1879524408\"]}";
		System.out.println(clOptions);

		byte[] imageData = Files.readAllBytes(Paths.get("./src/test/resources/Mickey_Mouse.png"));

		ClassifiedImages c = vrs.classifyWithCustomClassifier(clOptions, imageData);

		assertNotNull(c);
		System.out.println(c.toString());
	}

	@Test
	public void testClassifyWithAllCustomClassfiers() throws WatsonConfigurationException, FileNotFoundException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy("4ef8d82053abe8021bfbc7dc8ef6d6a40713292d");

		ClassifiedImages c = vrs.classifyWithAllCustomClassfiers("./src/test/resources/Mickey_Mouse.png");

		assertNotNull(c);
		System.out.println(c.toString());

		c = vrs.classifyWithAllCustomClassfiers("./src/test/resources/darth-vader.jpg");

		assertNotNull(c);
		System.out.println(c.toString());
	}
}
