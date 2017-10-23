package com.wds.watson.conversation.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class WatsonTrainingsSet {
	
	private String classfierClassName;
	private File zipFile;
	private String fileName;
	
	public WatsonTrainingsSet(String className, String zipFilePath, String fileName) throws FileNotFoundException {
		super();
		this.classfierClassName = className;
		this.setZipFileFromFileName(zipFilePath);
		this.fileName = fileName;
	}
	
	public WatsonTrainingsSet(String className, File zipFile, String fileName) {
		super();
		this.classfierClassName = className;
		this.zipFile = zipFile;
		this.fileName = fileName;
	}
	
	public String getClassfierClassName() {
		return classfierClassName;
	}
	public void setClassfierClassName(String className) {
		this.classfierClassName = className;
	}
	public File getZipFile() {
		return zipFile;
	}
	public void setZipFile(File zipFile) {
		this.zipFile = zipFile;
	}
	
	public void setZipFileFromFileName(String fileName) throws FileNotFoundException{
		this.zipFile = new File(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
