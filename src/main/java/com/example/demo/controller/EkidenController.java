package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.domain.dto.EkidenDto;
import com.example.demo.domain.dto.EkidenMembers;
import com.example.demo.domain.dto.EkidenOrder;
import com.example.demo.domain.helper.Console;
import com.example.demo.domain.service.EkidenService;
import com.example.demo.exception.BusinessException;
import com.example.demo.util.FileOperationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Valid
@Controller
@ResponseBody
@RequestMapping("/ekiden")
public class EkidenController {

	private static final String NOT_FOUND = "404 Not Found";

	private static final Logger logger = LoggerFactory.getLogger(EkidenController.class);

	/** ダウンロードを行うためのファイルのURL. */
	static final String DOWNLOAD_FILE_URL = "files/";
	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.
	private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

	@Autowired
	private EkidenService ekidenService;

	// @Autowired
	// private FileOperationUtils fileOperationUtils;

	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * SQLファイルの実行ログを見るためのメソッド
	 *
	 * @return
	 */
	@GetMapping("/check")
	public ResponseEntity<?> findBySqlFile() {

		ekidenService.operationCheckSqlFile();
		return ResponseEntity.ok().build();
	}

	/**
	 * チャンク転送の実装例
	 *
	 * @param chunckSize
	 *            チャンクサイズ
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/members")
	public ResponseEntity<StreamingResponseBody> getAllMembers(@RequestParam("chunckSize") Integer chunckSize)
			throws IOException {

		List<EkidenMembers> results = ekidenService.getAllMembers();
		if (results.size() == 0) {

			logger.debug(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}

		//チャンク形式でデータを送信
		StreamingResponseBody responseBody = null;
		try {
			responseBody = outputStream -> {
				Console.println("Start Async processing");

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
				String json = objectMapper.writeValueAsString(results);

				// TODO オブジェクトをJSON（文字列)に変換して、レスポンスに書き出す
				for (int off = 0; off < json.getBytes().length; off += chunckSize) {
					// バイト単位で切り出し
					int chunckSizeTrans = chunckSize;

					if (off + chunckSize > json.getBytes().length) {
						chunckSizeTrans = json.getBytes().length - off;
					}
					//json文字列をバイト単位で切り出す
					String jsonByte = new String(json.getBytes("UTF-8"), off, chunckSizeTrans, "UTF-8");
					outputStream.write(String.valueOf(chunckSize).getBytes());
					outputStream.write("\r\n".getBytes());
					outputStream.write(jsonByte.getBytes());
					// outputStream.write(json.getBytes());
					outputStream.write("\r\n".getBytes());
					outputStream.flush();
				}
				// 最後は空のチャンクを転送する。
				outputStream.write(String.valueOf("0").getBytes());
				outputStream.write("".getBytes());

			};
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			logger.error("INTERNAL SERVER ERROR");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		// ヘッダの設定
		// 戻り値がStreamingResponseBodyの場合は、ヘッダを設定できないため、戻り値をResponseEntity<StreamingResponseBody>にした
		HttpHeaders headers = new HttpHeaders();
		// headers.set("Content-Type", "application/json");
		//テスト用のヘッダ
		headers.set("Test-header", "test");
		return new ResponseEntity<StreamingResponseBody>(responseBody, headers, HttpStatus.OK);
	}

	//	@RequestMapping(method = RequestMethod.GET, path = "/direct")
	//	public StreamingResponseBody directStreaming(@RequestParam(defaultValue = "1") long eventNumber,
	//			@RequestParam(defaultValue = "0") long intervalSec, HttpServletResponse response) throws IOException {
	//
	//		// StreamingResponseBodyのwriteToメソッドの中に非同期処理を実装する
	//		// StreamingResponseBoydは関数型インタフェースなので、ラムダ式が使える
	//		StreamingResponseBody responseBody = outputStream -> {
	//			Console.println("Start Async processing");
	//
	//			if (intervalSec == 999) {
	//				throw new IllegalStateException("Special parameter for confirm error.");
	//			}
	//
	//			List<EkidenMembers> results = ekidenService.getAllMembers();
	//			if (results.size() == 0) {
	//
	//				response.setStatus(HttpStatus.NOT_FOUND.value());
	//			}
	//
	//			for (long i = 1; i <= eventNumber; i++) {
	//
	//				try {
	//					TimeUnit.SECONDS.sleep(intervalSec);
	//				} catch (InterruptedException e) {
	//					Thread.interrupted();
	//				}
	//				// outputStream.write(("msg" + i + "\r\n").getBytes());
	//
	//				for (EkidenMembers member : results) {
	//					outputStream.write(member.toString().length());
	//					outputStream.write(member.toString().getBytes());
	//					outputStream.write("\r\n".getBytes());
	//				}
	//				outputStream.flush();
	//			}
	//			Console.println("End Async processing");
	//
	//		};
	//
	//		Console.println("End get.");
	//		return responseBody;
	//	}

	/**
	 * チャンク転送実装例　ファイル読み込みバージョン
	 * @param eventNumber
	 * @param intervalSec
	 * @param fileName
	 * @param chunckSize
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/direct2")
	public StreamingResponseBody directStreaming2(@RequestParam(defaultValue = "1") long eventNumber,
			@RequestParam(defaultValue = "0") long intervalSec, @RequestParam("fileName") String fileName,
			@RequestParam("chunckSize") int chunckSize, HttpServletResponse response) throws IOException {

		// StreamingResponseBodyのwriteToメソッドの中に非同期処理を実装する
		// StreamingResponseBoydは関数型インタフェースなので、ラムダ式が使える
		StreamingResponseBody responseBody = outputStream -> {
			Console.println("Start Async processing");

			if (intervalSec == 999) {
				throw new IllegalStateException("Special parameter for confirm error.");
			}

			// ダウンロード対象ファイルの読み込
			File file = null;
			try {
				file = loadFile(DOWNLOAD_FILE_URL + fileName);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			RandomAccessFile input = new RandomAccessFile(file, "r");

			for (int start = 0; start <= 1000; start += chunckSize) {
				try {
					TimeUnit.SECONDS.sleep(intervalSec);
				} catch (InterruptedException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				outputStream.write(String.valueOf(chunckSize).getBytes());
				outputStream.write("\r\n".getBytes());
				FileOperationUtils.copy(input, outputStream, start, chunckSize);
				outputStream.write("\r\n".getBytes());
				outputStream.flush();
			}
			//空のチャンクを送信
			outputStream.write(String.valueOf(0).getBytes());

			Console.println("End Async processing");

		};

		Console.println("End get.");
		return responseBody;
	}

	/**
	 * チャンク転送(ファイル読み込みバージョン)
	 * @param eventNumber
	 * @param intervalSec
	 * @param fileName
	 * @param chunckSize
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/direct3")
	public ResponseEntity<StreamingResponseBody> directStreaming3(@RequestParam(defaultValue = "1") long eventNumber,
			@RequestParam(defaultValue = "0") long intervalSec, @RequestParam("fileName") String fileName,
			@RequestParam("chunckSize") int chunckSize, HttpServletResponse response) throws IOException {

		// StreamingResponseBodyのwriteToメソッドの中に非同期処理を実装する
		// StreamingResponseBoydは関数型インタフェースなので、ラムダ式が使える
		StreamingResponseBody responseBody = outputStream -> {
			Console.println("Start Async processing");

			if (intervalSec == 999) {
				throw new IllegalStateException("Special parameter for confirm error.");
			}

			// ダウンロード対象ファイルの読み込
			File file = null;
			try {
				file = loadFile(DOWNLOAD_FILE_URL + fileName);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			RandomAccessFile input = new RandomAccessFile(file, "r");

			for (int start = 0; start <= 1000; start += chunckSize) {
				try {
					TimeUnit.SECONDS.sleep(intervalSec);
				} catch (InterruptedException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				outputStream.write(String.valueOf(chunckSize).getBytes());
				outputStream.write("\r\n".getBytes());
				FileOperationUtils.copy(input, outputStream, start, chunckSize);
				outputStream.write("\r\n".getBytes());
				outputStream.flush();
			}

			Console.println("End Async processing");

		};

		Console.println("End get.");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Otameshi", "お試しヘッダバリュー");

		return new ResponseEntity<StreamingResponseBody>(responseBody, headers, HttpStatus.OK);
	}

	@GetMapping("/members-order")
	public ResponseEntity<EkidenDto> getAll() throws IOException {

		List<EkidenMembers> ekidenMembers = ekidenService.getAllMembers();
		if (ekidenMembers.size() == 0) {

			logger.debug(NOT_FOUND);

			return ResponseEntity.notFound().build();
		}

		List<EkidenOrder> orders = ekidenService.getAllOrders();
		if (orders.size() == 0) {
			logger.debug(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}

		EkidenDto ekidenDto = new EkidenDto(ekidenMembers, orders);

		return ResponseEntity.ok(ekidenDto);

	}

	@GetMapping("/members/{id}")
	public ResponseEntity<EkidenMembers> getMembersById(@PathVariable Integer id) {

		EkidenMembers member = ekidenService.getMemberById(id);
		if (member == null) {
			logger.debug(NOT_FOUND);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(member);
		}
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<EkidenOrder> getOrderById(@PathVariable Integer id) {

		EkidenOrder order = ekidenService.getOrderById(id);
		if (order == null) {
			logger.debug(NOT_FOUND);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(order);
		}
	}

	@GetMapping("/orders")
	public ResponseEntity<List<EkidenOrder>> getOrders() {

		List<EkidenOrder> orders = ekidenService.getAllOrders();
		if (orders.size() == 0) {
			logger.debug(NOT_FOUND);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(orders);
		}
	}

	@PostMapping("/create-teams")
	public ResponseEntity<EkidenDto> createTeam(@RequestBody EkidenDto ekidenDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		EkidenDto insertResult = ekidenService.createTeam(ekidenDto);

		if (insertResult == null) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} else {

			return ResponseEntity.status(HttpStatus.CREATED).body(insertResult);
		}

	}

	/**
	 * 駅伝の走順を更新する
	 *
	 * @param ekidenDto
	 * @param bindingResult
	 * @return
	 */
	@PutMapping
	public ResponseEntity<EkidenDto> updateOrder(@RequestBody EkidenDto ekidenDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return ResponseEntity.badRequest().build();
		}

		EkidenDto updateResult = ekidenService.updateOrder(ekidenDto);
		if (updateResult == null) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {

			return ResponseEntity.ok(updateResult);
		}

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

	@ExceptionHandler
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
		logger.error("timeout error is occurred.", e);
		return "timeout!!";
	}

}
