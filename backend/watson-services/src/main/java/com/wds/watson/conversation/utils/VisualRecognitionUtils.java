package com.wds.watson.conversation.utils;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;

public class VisualRecognitionUtils {
	
	private static final String CL_IDS_KEY = "classifier_ids";
	private static final String OWNERS_KEY = "owners";
	private static final String SQ_BRACKET_OP = "[";
	private static final String SQ_BRACKET_CL = "]";
	private static final String CURL_BRACES_OP = "{";
	private static final String CURL_BRACES_CL = "}";
	private static final String ESC_DOUB_QUO = "\"";
	private static final String DOUB_DOT = ":";
	private static final String COMMA = ",";
	
	public static String createPropertiesStringForCustomClassifier(Classifiers classfiers) {

		StringBuilder cl = new StringBuilder();
		for (Classifier c : classfiers.getClassifiers()) {
			cl.append(ESC_DOUB_QUO).append(c.getClassifierId()).append(ESC_DOUB_QUO).append(COMMA);
		}

		int i = cl.length();
		String classfierIds = cl.substring(0, i - 1);

		cl.setLength(0);

		return cl.append(CURL_BRACES_OP).append(ESC_DOUB_QUO).append(CL_IDS_KEY).append(ESC_DOUB_QUO).append(DOUB_DOT)
				.append(SQ_BRACKET_OP).append(classfierIds).append(SQ_BRACKET_CL).append(CURL_BRACES_CL).toString();
	}

}
