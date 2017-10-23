package com.wds.watson.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.conversation.utils.WatsonTrainingsSet;

public interface VisualRecognitionService {
	
	public Classifier trainCustomClassifier(String classfierId, List<WatsonTrainingsSet> trainigsSet, List<WatsonTrainingsSet> negativExample) throws FileNotFoundException;
	
	public Classifier createCustomClassifier(String classfierName, List<WatsonTrainingsSet> trainigsSet, List<WatsonTrainingsSet> negativExample) throws FileNotFoundException, WatsonConfigurationException;
	
	public ClassifiedImages processImage(String pathToImage) throws FileNotFoundException;
	
	public ClassifiedImages processImage(File image) throws FileNotFoundException;
	
	public ClassifiedImages processImage(InputStream inputData);
	
	public DetectedFaces performFaceRecognition(File image) throws FileNotFoundException;
	
	public DetectedFaces performFaceRecognition(String pathToimage) throws FileNotFoundException;
	
	public Classifiers getAllCustomClassifiers();
	
	public Classifier getCustomClassifierDetails(String classifierId);

	public ClassifiedImages classify(String pathToImage, Classifiers classifiers) throws FileNotFoundException;
	
	public ClassifiedImages classifyWithAllCustomClassfiers(String pathToImage) throws FileNotFoundException;

	public ClassifiedImages classifyWithCustomClassifier(String customClassfierProperty, String pathToImage) throws FileNotFoundException;

	ClassifiedImages classifyWithCustomClassifier(String customClassfierProperty, byte[] imageData);

}
