package com.wds.watson.rest.domain;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;

import java.util.List;

public class ImageClassifiersDto {
	private List<ClassifierResult> classifiers;

	public ImageClassifiersDto(List<ClassifierResult> classifiers) {
		this.classifiers = classifiers;
	}

	public List<ClassifierResult> getClassifiers() {
		return classifiers;
	}

	public void setClassifiers(List<ClassifierResult> classifiers) {
		this.classifiers = classifiers;
	}
}
