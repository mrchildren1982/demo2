package com.example.demo.hoge.domain.dto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class LargeFileStreamingFileFormatResponseBody implements StreamingResponseBody {

	private static final Logger logger = LoggerFactory.getLogger(LargeFileStreamingFileFormatResponseBody.class);

	private static final String FILE_PATH = "";

	private static final int BUFFER_SIZE = 1 * 1024 * 1024;

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

		logger.debug("Start Async Processing");

		try (InputStream input = new BufferedInputStream(new FileInputStream(FILE_PATH))) {

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			int total = 0;
			while ((len = input.read(buffer)) != -1) {
				outputStream.write(buffer, 0,len);
				outputStream.flush();
				total += len;
				logger.debug("End Async Processing");
			}
		}





	}

}
