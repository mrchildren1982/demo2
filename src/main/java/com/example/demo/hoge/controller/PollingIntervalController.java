package com.example.demo.hoge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.exception.BusinessException;
import com.example.demo.hoge.domain.dto.LargeFileStreamingResponseBody;
import com.example.demo.hoge.domain.dto.PollingIntervals;
import com.example.demo.hoge.domain.service.PollingIntervalService;

@RestController
@RequestMapping("/polling-interval")
public class PollingIntervalController {

	private static final Logger logger = LoggerFactory.getLogger(PollingIntervalController.class);

	@Autowired
	PollingIntervalService pollingIntervalService;

	/**
	 * 遠隔操作ポーリング間隔検索
	 *
	 * @param stbSerial　STBシリアル番号
	 * @param defaultSearchFlag デフォルト値も検索フラグ
	 * @return
	 */
	@GetMapping("/{stbSerial}")
	public ResponseEntity<StreamingResponseBody> getPollingInterval(@PathVariable("stbSerial") String stbSerial,
			@RequestParam("defaultSearchFlag") String defaultSearchFlag, @RequestParam("chunckSize") Integer chunckSize
			) {

		PollingIntervals pollngIntervals;
		try {
			pollngIntervals = pollingIntervalService.getPollingInterval(stbSerial,transformToBoolean(defaultSearchFlag));
			if (pollngIntervals == null) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		LargeFileStreamingResponseBody largeFile = new LargeFileStreamingResponseBody(pollngIntervals, chunckSize);

		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<StreamingResponseBody>(
				largeFile, responseHeaders , HttpStatus.OK);

	}

	/**
	 * 文字列をBoolean値に変換する
	 *
	 * @param target　対象文字列
	 * @return　変換結果
	 */
	private Boolean transformToBoolean(String target) {

		if (target == null || target.length() == 0) {
			return false;
		}
		if (target.equalsIgnoreCase("true")) {
			return true;
		}
		if (target.equals("1")) {
			return true;
		}

		return false;

	}

}
