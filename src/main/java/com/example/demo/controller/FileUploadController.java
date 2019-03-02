package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.dao.TUploadFileDao;
import com.example.demo.domain.entity.TUploadFile;

@RestController
public class FileUploadController {

	/** ファイルアップロード成功時のメッセージ */
	private static final String SUCCESS_MESSAGE = "You successfully uploaded.";
	/** ファイルアップロード失敗時のメッセージ */
	private static final String FAILURE_MASSAGE = "You fail.";
	/** フォルダ名に設定する現在時刻のフォーマット */
	private static final String YYYY_M_MDD_H_HMMSS_SSS = "yyyyMMddHHmmssSSS";
	/** アップロードファイルデータ格納テーブルDAO　*/
	@Autowired
	private TUploadFileDao dao;

	@RequestMapping(method = RequestMethod.POST, value = "/post")
	public Object post(
			@RequestParam("upload_file") MultipartFile multipartFile) {

		return uploadFile(multipartFile);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/post/hoge")
	public Object post2(
			@RequestParam("upload_file1") MultipartFile multipartFile1,
			@RequestParam("upload_file2") MultipartFile multipartFile2) {

		return uploadFile(multipartFile1, multipartFile2);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/post/hoge/multi")
	public Object post3(
			@RequestParam("upload_file1") MultipartFile multipartFile1,
			@RequestParam("upload_file2") MultipartFile multipartFile2,
			@RequestParam("upload_file3") MultipartFile multipartFile3) {

		return uploadFile(multipartFile1, multipartFile2, multipartFile3);

	}


	private String uploadFile(MultipartFile multipartFile) {
		// ファイルが空の場合は異常終了
		if (multipartFile.isEmpty()) {
			// 異常終了時の処理
		}

		// ファイル種類から決まる値をセットする
		StringBuffer filePath = new StringBuffer("/uploadfile")
				.append(File.separator).append("json"); //ファイルパス

		// アップロードファイルを格納するディレクトリを作成する
		File uploadDir = mkdirs(filePath);

		try {
			uploadFile(multipartFile, uploadDir);

			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			// 異常終了時の処理
		} catch (Throwable t) {
			// 異常終了時の処理
		}

		return FAILURE_MASSAGE;
	}

	private String uploadFile(MultipartFile multipartFile1, MultipartFile multipartFile2) {
		// ファイルが空の場合は異常終了
		if (multipartFile1.isEmpty() || multipartFile2.isEmpty()) {
			// 異常終了時の処理
		}

		// ファイル種類から決まる値をセットする
		StringBuffer filePath = new StringBuffer("/uploadfile")
				.append(File.separator).append("json"); //ファイルパス

		// アップロードファイルを格納するディレクトリを作成する
		File uploadDir = mkdirs(filePath);

		try {
			//ファイルをアップロードする
			uploadFile(multipartFile1, uploadDir);
			//ファイルをアップロードする
			uploadFile(multipartFile2, uploadDir);

			//DBに登録する
			insertUploadFileData(multipartFile1, 1);
			insertUploadFileData(multipartFile2, 2);

			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			// 異常終了時の処理
			e.printStackTrace();
		} catch (Throwable t) {
			// 異常終了時の処理
		}

		return FAILURE_MASSAGE;
	}

	private String uploadFile(MultipartFile... multipartFiles) {

		int id = 1;

		// ファイル種類から決まる値をセットする
		StringBuffer filePath = new StringBuffer("/uploadfile")
				.append(File.separator).append("json"); //ファイルパス

		// アップロードファイルを格納するディレクトリを作成する
		File uploadDir = mkdirs(filePath);

		for (MultipartFile multipartFile : multipartFiles) {
			// ファイルが空の場合は異常終了
			if (multipartFile.isEmpty()) {

				return FAILURE_MASSAGE;

			}
			try {
				//ファイルをアップロードする
				uploadFile(multipartFile, uploadDir);
				//DBに登録する
				insertUploadFileData(multipartFile, id);
				id++;
			} catch (Exception e) {
				// 異常終了時の処理
				e.printStackTrace();
				return FAILURE_MASSAGE;

			} catch (Throwable t) {
				// 異常終了時の処理
				return FAILURE_MASSAGE;

			}

		}
		return SUCCESS_MESSAGE;
	}

	private TUploadFile insertUploadFileData(MultipartFile multipartFile1, int id) throws IOException {
		TUploadFile dbData = new TUploadFile();
		dbData.setId(id);
		//			dbData.setId(dao.selectMaxId() + 1);
		dbData.setFileData(multipartFile1.getBytes());

		dao.insert(dbData);
		return dbData;
	}

	/**
	 * ファイルをアップロードする
	 *
	 * @param multipartFile MultipartFile
	 * @param uploadDir アップロードディレクトリ
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void uploadFile(MultipartFile multipartFile, File uploadDir) throws IOException, FileNotFoundException {
		String fileType = "json";
		//ファイル名
		String filePath = uploadDir.getPath() + "/" + multipartFile.getOriginalFilename() + "." + fileType;

		File uploadFile = new File(filePath);
		byte[] bytes = multipartFile.getBytes();

		try (BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));) {
			uploadFileStream.write(bytes);
			//			uploadFileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * アップロードファイルを格納するディレクトリを作成する
	 *
	 * @param filePath
	 * @return
	 */
	private File mkdirs(StringBuffer filePath) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_M_MDD_H_HMMSS_SSS);
		File uploadDir = new File(filePath.toString(), formatter.format(now));
		// 既に存在する場合はプレフィックスをつける
		int prefix = 0;
		while (uploadDir.exists()) {
			prefix++;
			uploadDir = new File(filePath.toString() + formatter.format(now) + "-" + String.valueOf(prefix));
		}

		// フォルダ作成
		uploadDir.mkdirs();

		return uploadDir;
	}

}
