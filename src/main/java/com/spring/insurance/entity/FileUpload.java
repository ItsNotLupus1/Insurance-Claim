package com.spring.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EnableAutoConfiguration
public class FileUpload {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String fileName;
	private String filepath;
	
	private Long holder_id;
	
	public Long getHolder_id() {
		return holder_id;
	}
	public void setHolder_id(Long holder_id) {
		this.holder_id = holder_id;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
