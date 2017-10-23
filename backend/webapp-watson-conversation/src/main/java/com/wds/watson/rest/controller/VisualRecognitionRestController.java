package com.wds.watson.rest.controller;

import java.io.ByteArrayInputStream;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.wds.watson.configuration.exception.WatsonConfigurationException;
import com.wds.watson.rest.domain.ClassifyImageDto;
import com.wds.watson.rest.domain.ImageClassifiersDto;
import com.wds.watson.services.VisualRecognitionService;
import com.wds.watson.services.WatsonServiceFactory;

/**
 * Rest-Controller für Service-Zugriff auf Visual Recoginition API
 */
@RestController
@RequestMapping("/api/vrec")
public class VisualRecognitionRestController {

	private static final Logger LOG = LoggerFactory.getLogger(VisualRecognitionRestController.class);

	/**
	 * Watson Visual Recognition API-Key
	 */
	private static final String API_KEY = "enter API Key";

	/**
	 * Classifies an image
	 *
	 * @param payload
	 * @return
	 * @throws WatsonConfigurationException
	 */
	@RequestMapping(path = "/classify", method = RequestMethod.POST)
	public ImageClassifiersDto classifyImage(@RequestBody ClassifyImageDto payload)
			throws WatsonConfigurationException {

		VisualRecognitionService vrs = WatsonServiceFactory.getVisualRecognitionServiceWithOptionalProxy(API_KEY);

		String clOptions = getClassifierOptions();

		// convert Base64 String to Binary
		byte[] imageData = DatatypeConverter.parseBase64Binary(payload.getImageDataBase64());

		ClassifiedImages classifiedImages = vrs.classifyWithCustomClassifier(clOptions, imageData);

		if (isNotClassified(classifiedImages)) {

			System.out.println("No Classification Result ... falling to default");

			//wenn mit den Custom Classifiern nichts gefunden wurde, probieren wir einfach den IBM default-Classifier
			classifiedImages = vrs.processImage(new ByteArrayInputStream(imageData));

			if (isNotClassified(classifiedImages)) {
				return new ImageClassifiersDto(null);
			}
		}

		return new ImageClassifiersDto(classifiedImages.getImages().get(0).getClassifiers());
	}

	/**
	 * Liefert die JSON-Options mit allen Classifiers für den API-Zugriff
	 *
	 * @return
	 */
	private String getClassifierOptions() {
		return "{\"classifier_ids\": [" +
					"\"classfierID1\", " +
					"\"classfierID2\", " +
				"]}";
	}

	private boolean isNotClassified(ClassifiedImages classifiedImages) {
		return classifiedImages == null || classifiedImages.getImages() == null
				|| classifiedImages.getImages().isEmpty()
				|| classifiedImages.getImages().get(0).getClassifiers() == null
				|| classifiedImages.getImages().get(0).getClassifiers().isEmpty();
	}
}
