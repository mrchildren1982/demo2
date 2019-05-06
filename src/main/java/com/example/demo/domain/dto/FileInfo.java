package com.example.demo.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class FileInfo implements Serializable{

	public String filePath;

	public String contentType;

	public String checkSum;

	public String downloadFileName;

	public Long contentLength;

}
