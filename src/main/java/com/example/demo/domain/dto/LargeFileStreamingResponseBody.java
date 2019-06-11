package com.example.demo.domain.dto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * ★ポイント1
 * org.springframework.web.servlet.mvc.method.annoation.StreamingResponseBodyインタフェースを実装したクラスを定義する
 *
 * Implements StreamingResponseBody class for large file download
 * @author tbk40
 *
 */
public class LargeFileStreamingResponseBody implements StreamingResponseBody {

	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory.getLogger(LargeFileStreamingResponseBody.class);

	/**
	 * ★ポイント2
	 * バッファリングするデータサイズの定義
	 * buffer size 1MB
	 */
	private static final int BUFFER_SIZE = 1 * 1024 * 1024;

	/**
	 * ★ポイント3
	 * コンストラクタでダウンロード対象となるファイルのパスを引数として受け取る
	 * filePath of download file
	 *
	 */
	private final String filePath;

	public LargeFileStreamingResponseBody(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * ★ポイント4
	 * このメソッドの処理がHTTPリクエストを受け付けるスレッドとは別に非同期で実行される
	 * asynchronous writing for buffered content data
	 * @see org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody#writeTo(java.io.OutputStream)
	 */
	@Override
	public void writeTo(OutputStream outputStream) throws IOException {

		logger.debug("Start async processing.");
		try (InputStream input = new BufferedInputStream(new FileInputStream(filePath))) {

			byte[] buffer = new byte[BUFFER_SIZE];
			long total = 0;
			int len = 0;
			while ((len = input.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
				total = total + len;
				logger.debug("End async processing");
			}
		}

	}

}
