package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.domain.dto.FileInfo;
import com.example.demo.domain.dto.LargeFileStreamingResponseBody;
import com.example.demo.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/large-file-download")
public class LargeFileDownloadController {

	private static final Logger logger = LoggerFactory.getLogger(LargeFileDownloadController.class);

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ObjectMapper objectMapper;

//	@Value("${app.sample.fileInfo:C:/temp/download/fileInfo.json}")
	private File downloadFileInfo;

	@RequestMapping(method = RequestMethod.GET, value = "/get")
	public ResponseEntity<FileInfo> getFileInfo() {

		FileInfo fileInfo = new FileInfo();
		fileInfo.setCheckSum("3f31020b65ffc7b230cfb3d4f87a792000f41784");
		fileInfo.setContentType("application/zip");
		fileInfo.setContentLength(Long.valueOf("118065523"));
		fileInfo.setDownloadFileName("gradle-4.10.3-all.zip");
		fileInfo.setFilePath("C:\\temp\\download\\gradle-4.10.3-all.zip");

		return ResponseEntity.ok(fileInfo);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/chunked")
	public ResponseEntity<StreamingResponseBody> chunkedDownload() {

		logger.debug("[start]");

		FileInfo fileInfo = getDownloadFile();

		StreamingResponseBody responseBody = new LargeFileStreamingResponseBody(fileInfo.getFilePath());

		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("Content-Type", fileInfo.getContentType());

		//Transfer-Encoding; chuncked になるので設定してはだめ
		//		responseHeaders.set("Content-Length", Long.toString(fileInfo.getContentLength()));

		responseHeaders.set("X-SHA1-CheckSum", fileInfo.getCheckSum());
		responseHeaders.set("Content-Disposition", "attachment; filename=" + fileInfo.getDownloadFileName());

		ResponseEntity<StreamingResponseBody> responseEntity = new ResponseEntity<StreamingResponseBody>(
				responseBody, responseHeaders, HttpStatus.OK);

		logger.debug("[end]");
		return responseEntity;

	}

	private FileInfo getDownloadFile() {

				try {
					downloadFileInfo = loadFile("files/fileInfo.json");
				} catch (BusinessException e1) {
					logger.error(e1.getMessage());
				}

//		String json = "{\r\n" +
//				"    \"filePath\": \"C:\\\\temp\\\\download\\\\markdowndemo.md\",\r\n" +
//				"    \"contentType\": \"application/zip\",\r\n" +
//				"    \"checkSum\": \"3f31020b65ffc7b230cfb3d4f87a792000f41784\",\r\n" +
//				"    \"downloadFileName\": \"markdowndemo.md\",\r\n" +
//				"    \"contentLength\": 118065523\r\n" +
//				"}";

		FileInfo fileInfo = null;
		try {
			//			fileInfo = objectMapper.readValue(downloadFileInfo, FileInfo.class);
			fileInfo = objectMapper.readValue(downloadFileInfo, FileInfo.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return fileInfo;
	}

	/**
	 * ファイルを読み込む。IOExceptionが発生した場合はnullを返却。
	 *
	 * @param filePath
	 *            ファイルパス
	 * @return File File
	 * @throws IOException
	 */
	private File loadFile(String filePath) throws BusinessException {

		Resource resource = resourceLoader.getResource("classpath:" + filePath);
		File file = null;
		try {
			file = resource.getFile();
		} catch (IOException e) {
			throw new BusinessException("ファイルが存在しません");
		}
		return file;
	}
}
