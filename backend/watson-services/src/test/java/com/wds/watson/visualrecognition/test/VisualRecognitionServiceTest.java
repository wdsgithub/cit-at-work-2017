package com.wds.watson.visualrecognition.test;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.services.VisualRecognitionService;
import com.wds.watson.services.WatsonServiceFactory;

public class VisualRecognitionServiceTest {

	static final String APIKEY = "22c6a82f53cac3e81a3fb2040cfb4f7c212931d2";
	
	@Test
	public void testKitty() throws FileNotFoundException, WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy(APIKEY);
		
		ClassifiedImages cImg = vrs.processImage("./src/test/resources/kitty.jpg");
		assertNotNull(cImg);
		System.out.println(cImg.toString());
	}
	
	@Test
	public void testKittyInputStrea() throws WatsonConfigurationException, IOException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy(APIKEY);
		
		byte[] inputData = Files.readAllBytes(Paths.get("./src/test/resources/kitty.jpg"));
		
		ClassifiedImages cImg = vrs.processImage(new ByteArrayInputStream(inputData));
		assertNotNull(cImg);
		System.out.println(cImg.toString());
	}
	
	@Test
	public void testDarthVader() throws FileNotFoundException, WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy(APIKEY);
		
		ClassifiedImages cImg = vrs.processImage("./src/test/resources/darth-vader.jpg");
		assertNotNull(cImg);
		
		System.out.println(cImg.toString());
	}
	
	@Test
	public void testLeoMessi() throws FileNotFoundException, WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy(APIKEY);
		
		ClassifiedImages cImg = vrs.processImage("./src/test/resources/leo-messi.jpg");
		assertNotNull(cImg);
		System.out.println("Classifierts for Image: " + cImg.getImages().get(0).getClassifiers().get(0).getClasses().size());
		System.out.println(cImg.toString());
	}
	
	@Test
	public void testPutinFaceRecognition() throws FileNotFoundException, WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory
				.getVisualRecognitionServiceWithDefaultWdsProxy(APIKEY);
		
		DetectedFaces cImg = vrs.performFaceRecognition("./src/test/resources/putin.jpg");
		assertNotNull(cImg);
		//System.out.println("Faces found: " + cImg.getImages().get(0).getFaces().size());
		System.out.println(cImg.toString());
	}
	
	@Test(expected=WatsonConfigurationException.class)
	public void testWatsonConfigExceptionNull() throws WatsonConfigurationException{
		WatsonServiceFactory.getVisualRecognitionServiceWithDefaultWdsProxy(null);
	}
	
	@Test(expected=WatsonConfigurationException.class)
	public void testWatsonConfigExceptionEmpty() throws WatsonConfigurationException{
		WatsonServiceFactory.getVisualRecognitionServiceWithDefaultWdsProxy("");
	}
}
