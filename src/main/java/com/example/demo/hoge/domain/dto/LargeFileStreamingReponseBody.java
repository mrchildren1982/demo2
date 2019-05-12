package com.example.demo.hoge.domain.dto;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.domain.dto.LargeFileStreamingResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 大容量ファイルチャンク転送用ラッパークラス
 *
 * @author 雅幸
 *
 */
public class LargeFileStreamingReponseBody implements StreamingResponseBody {

	/** 改行コード(Carriage Reteturn Line Feed) */
	private static final String CRLF = "\r\n";

	/**
	 * LOGGER
	 */
	private static final Logger logger = LoggerFactory.getLogger(LargeFileStreamingResponseBody.class);

	private static final int BUFFER_SIZE = 1 * 1024 * 1024;

	/** リソースクラス */
	private Object resource;
	/** チャンクサイズ */
	private Integer chunckSize;

	public LargeFileStreamingReponseBody() {

	}

	public LargeFileStreamingReponseBody(Object resource, Integer chunckSize) {

		this.resource = resource;
		this.chunckSize = chunckSize;

	}

	@Override
	public void writeTo(OutputStream out) throws IOException {

		logger.debug("Start Async Processing");

		//リソースオブジェクトをJSON文字列に変換する
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = mapper.writeValueAsString(resource);

		//チャンクサイズで切り取ってoutputStreamに書き込む
		for (int off = 0; off < json.getBytes().length; off += chunckSize) {

			int chunckSizeTrans = chunckSize;

			if (off + chunckSize > json.getBytes().length) {
				chunckSizeTrans  = json.getBytes().length - off;
			}

			String jsonByte =  new String(json.getBytes("UTF-8"), off, chunckSizeTrans, "UTF-8");
			out.write(String.valueOf(chunckSize).getBytes());
			out.write(CRLF.getBytes());
			out.write(jsonByte.getBytes());
			// out.write(json.getBytes());
			out.write(CRLF.getBytes());
			out.flush();
		}

	}

}
