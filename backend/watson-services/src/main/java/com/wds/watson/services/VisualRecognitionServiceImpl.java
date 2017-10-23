package com.wds.watson.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.GetClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ListClassifiersOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.UpdateClassifierOptions;
import com.wds.watson.configuration.CustomProxyConfiguration;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.conversation.utils.VisualRecognitionUtils;
import com.wds.watson.conversation.utils.WatsonTrainingsSet;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;

class VisualRecognitionServiceImpl implements VisualRecognitionService {

	private VisualRecognition visualRecognition;
	private String apiKey;

	public VisualRecognitionServiceImpl() {

	}

	public VisualRecognitionServiceImpl(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public ClassifiedImages processImage(String pathToImage) throws FileNotFoundException {
		return this.processImage(new File(pathToImage));
	}

	@Override
	public ClassifiedImages processImage(File image) throws FileNotFoundException {

		ClassifyOptions options = new ClassifyOptions.Builder().imagesFile(image).build();
		ClassifiedImages result = visualRecognition.classify(options).execute();
		return result;

	}

	@Override
	public ClassifiedImages processImage(InputStream inputData) {
		ClassifyOptions options = new ClassifyOptions.Builder().imagesFile(inputData).imagesFilename("temp.jpeg").build();
		ClassifiedImages result = visualRecognition.classify(options).execute();
		return result;
	}

	@Override
	public DetectedFaces performFaceRecognition(File image) throws FileNotFoundException {

		DetectFacesOptions options = new DetectFacesOptions.Builder().imagesFile(image).build();
		DetectedFaces response = visualRecognition.detectFaces(options).execute();
		return response;
	}

	@Override
	public DetectedFaces performFaceRecognition(String pathToimage) throws FileNotFoundException {
		return this.performFaceRecognition(new File(pathToimage));
	}

	@Override
	public Classifiers getAllCustomClassifiers() {

		ListClassifiersOptions options = new ListClassifiersOptions.Builder().build();
		Classifiers cf = visualRecognition.listClassifiers(options).execute();
		return cf;
	}

	@Override
	public Classifier getCustomClassifierDetails(String classifierId) {

		GetClassifierOptions getClOptions = new GetClassifierOptions.Builder().classifierId(classifierId).build();
		Classifier cf = visualRecognition.getClassifier(getClOptions).execute();
		return cf;
	}

	@Override
	public ClassifiedImages classifyWithCustomClassifier(String customClassfierProperty, String pathToImage)
			throws FileNotFoundException {

		System.out.println("Classifing with Properties.... " + customClassfierProperty);

		ClassifyOptions options = new ClassifyOptions.Builder().parameters(customClassfierProperty)
				.imagesFile(new File(pathToImage)).build();
		ClassifiedImages result = visualRecognition.classify(options).execute();
		return result;
	}

	@Override
	public ClassifiedImages classifyWithCustomClassifier(String customClassfierProperty, byte[] imageData) {

		System.out.println("Classifing with Properties.... " + customClassfierProperty);

		ClassifyOptions options = new ClassifyOptions.Builder().parameters(customClassfierProperty)
				.imagesFile(new ByteArrayInputStream(imageData)).imagesFilename("test.jpeg").build();
		return visualRecognition.classify(options).execute();
	}

	@Override
	public ClassifiedImages classify(String pathToImage, Classifiers classifiers) throws FileNotFoundException {
		String classifyProps = VisualRecognitionUtils.createPropertiesStringForCustomClassifier(classifiers);
		return this.classifyWithCustomClassifier(classifyProps, pathToImage);
	}

	@Override
	public ClassifiedImages classifyWithAllCustomClassfiers(String pathToImage) throws FileNotFoundException {
		Classifiers classifiers = this.getAllCustomClassifiers();
		return this.classify(pathToImage, classifiers);
	}

	@Override
	public Classifier trainCustomClassifier(String classfierId, List<WatsonTrainingsSet> trainigsSet,
			List<WatsonTrainingsSet> negativExample) throws FileNotFoundException {

		UpdateClassifierOptions.Builder updateOptionsBuilder = new UpdateClassifierOptions.Builder(classfierId);
		if (negativExample != null && negativExample.size() > 0) {
			WatsonTrainingsSet neg = negativExample.get(0);
			updateOptionsBuilder.negativeExamples(neg.getZipFile());
			if (StringUtils.isNotEmpty(neg.getFileName())) {
				updateOptionsBuilder.negativeExamplesFilename(neg.getFileName());
			}
		}

		if (trainigsSet != null) {
			for (WatsonTrainingsSet w : trainigsSet) {
				updateOptionsBuilder.addClass(w.getClassfierClassName(), w.getZipFile());
			}
		}

		UpdateClassifierOptions options = updateOptionsBuilder.build();
		return visualRecognition.updateClassifier(options).execute();
	}

	@Override
	public Classifier createCustomClassifier(String classfierName, List<WatsonTrainingsSet> trainigsSet,
			List<WatsonTrainingsSet> negativExample) throws FileNotFoundException, WatsonConfigurationException {

		if (trainigsSet == null || trainigsSet.size() < 2) {
			throw new WatsonConfigurationException("Trainingsset may not be null or empty and needs at least 2 Trainingssets!");
		}

		CreateClassifierOptions.Builder createOptionsBuilder = new CreateClassifierOptions.Builder().name(classfierName);
		if (negativExample != null && negativExample.size() > 0) {
			WatsonTrainingsSet neg = negativExample.get(0);
			createOptionsBuilder.negativeExamples(neg.getZipFile());
			if (StringUtils.isNotEmpty(neg.getFileName())) {
				createOptionsBuilder.negativeExamplesFilename(neg.getFileName());
			}
		}

		for (WatsonTrainingsSet w : trainigsSet) {
			createOptionsBuilder.addClass(w.getClassfierClassName(), w.getZipFile());
		}
		
		CreateClassifierOptions options = createOptionsBuilder.build();
		System.out.println(options.toString());
		return visualRecognition.createClassifier(options).execute();

	}

	// Initialize IBM Service
	void initialize() {
		visualRecognition = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20, apiKey);
	}

	void initializeWithProxy(CustomProxyConfiguration customProxyConfig) {
		visualRecognition = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20, apiKey) {
			@Override
			protected OkHttpClient configureHttpClient() {
				Authenticator proxyAuthenticator = OkHttpClientProxyUtils.createAuthentification(customProxyConfig);
				Proxy proxy = OkHttpClientProxyUtils.createProxy(customProxyConfig);
				return super.configureHttpClient().newBuilder().proxy(proxy).proxyAuthenticator(proxyAuthenticator)
						.build();
			}
		};
	}

}
