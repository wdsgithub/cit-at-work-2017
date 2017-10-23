package com.wds.watson.rest.domain;

/**
 * Container für Daten zur Bilderkennung
 */
public class ClassifyImageDto {
	private String imageDataBase64;

	public String getImageDataBase64() {
		return imageDataBase64;
	}

	public void setImageDataBase64(String imageDataBase64) {
		this.imageDataBase64 = imageDataBase64;
	}
}
