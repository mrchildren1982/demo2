package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.enums.Range;
import com.example.demo.exception.BusinessException;
import com.example.demo.utils.FileOperationUtils;

@RestController
@ResponseBody
@Validated
@RequestMapping("/xxx")
public class HttpResumeController3 {

	@Autowired
	private ResourceLoader resourceLoader;

	/** ダウンロードを行うためのファイルのURL. */
	static final String DOWNLOAD_FILE_URL = "files/";
	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.
	private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

	// @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping("/{fileName}")
	public void requestFiles(HttpServletResponse response, @PathVariable("fileName") @NotEmpty String fileName,
			@RequestHeader("sessionId") @NotEmpty String sessionId,
			@RequestHeader("Range") @NotEmpty @Pattern(regexp = "^bytes=(\\d)*-(\\d)*(,(\\d)*-(\\d)*)*$") @NotEmpty String rangeHeader,
			@RequestHeader("proxyFlag") @NotEmpty String proxyFlag,
			@RequestHeader("argorithm") @Max(3) @Min(1)Integer argorithm) throws IOException {
		// ファイル読み込み
		File file = null;
		try {
			file = loadFile(DOWNLOAD_FILE_URL + fileName);
		} catch (BusinessException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		if (!file.exists()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		long length = file.length();
		long lastModified = file.lastModified();

		Range full = new Range(0, length - 1, length);
		List<Range> ranges = new ArrayList<>();

		//Rangeヘッダの文字列を解析して、読み込むファイルの開始位置と終了位置、長さを求める
		if (!setRanges(rangeHeader, length, ranges)) {
			response.setHeader("Content-Range", "bytes */" + length);
			response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return;
		}

		// Initialize response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setHeader("Accept-Ranges", "bytes");
		response.setDateHeader("Last-Modified", lastModified);

		RandomAccessFile input = null;
		OutputStream output = null;

		try {
			input = new RandomAccessFile(file, "rw");
			output = response.getOutputStream();

			if (ranges.isEmpty() || ranges.get(0) == full) {

				Range r = full;
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Length", String.valueOf(r.length));
				if (argorithm == 1) {
					FileOperationUtils.copy(input, output, r.start, r.length);
				} else if (argorithm == 2) {
					FileOperationUtils.copyFast(input, output, r.start, r.length);
				} else {
					FileOperationUtils.copy2(input, output, r.start,r.length);
				}

				// TODO
//				copyFirstVersion(input, output, r.start, r.end);

			} else if (ranges.size() == 1) {

				Range r = ranges.get(0);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total);
				response.setHeader("Content-Length", String.valueOf(r.length));
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.
				// TODO
				if (argorithm == 1) {
					FileOperationUtils.copy(input, output, r.start, r.length);
				} else if (argorithm == 2){
					FileOperationUtils.copyFast(input, output, r.start, r.length);
				} else {
					FileOperationUtils.copy2(input , output, r.start,r.length);
				}

//				copyFirstVersion(input, output, r.start, r.end);
			} else {

				// Return multiple parts of file.
				response.setContentType("multipart/byteranges; boundary=" + MULTIPART_BOUNDARY);
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

				ServletOutputStream sos = (ServletOutputStream) output;

				// Copy multi part range.
				for (Range r : ranges) {
					// Add multipart boundary and header fields for every range.
					sos.println();
					sos.println("--" + MULTIPART_BOUNDARY);
					sos.println("Content-Type: " + "application/octet-stream");
					sos.println("Content-Range: bytes " + r.start + "-" + r.end + "/" + r.total);

					// Copy single part range of multi part range.
					// //TODO
					if (argorithm == 1) {
						FileOperationUtils.copy(input, output, r.start, r.length);
					} else if (argorithm == 2){
						FileOperationUtils.copyFast(input, output, r.start, r.length);
					} else {
						FileOperationUtils.copy2(input, output, r.start, r.length);
					}

//					copyFirstVersion(input, output, r.start, r.length);
					// End with multipart boundary.
					sos.println();
					sos.println("--" + MULTIPART_BOUNDARY + "--");
				}
			}
		} catch (IOException e) {

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * ヘッダに設定された文字列から読み込むファイルの開始位置と終了位置、長さを求める
	 * @param rangeHeader
	 * @param length
	 * @param ranges
	 */
	public boolean setRanges(String rangeHeader, long length, List<Range> ranges) {
		if (ranges.isEmpty()) {
			for (String part : rangeHeader.substring(6).split(",")) {

				long start = sublong(part, 0, part.indexOf("-"));
				long end = sublong(part, part.indexOf("-") + 1, part.length());

				if (start == -1) {
					start = length - end;
					end = length - 1;
				} else if (end == -1 || end > length - 1) {
					end = length - 1;
				}

				if (start > end) {

					return false;
				}
				ranges.add(new Range(start, end, length));

			}
		}
		return true;
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

	/**
	 * 文字列の指定された開始位置と指定された終了位置の部分を切り出す。
	 *
	 * @param value
	 *            文字列
	 * @param beginIndex
	 *            開始インデックス
	 * @param endIndex
	 *            終了インデックス
	 * @return
	 */
	private static long sublong(String value, int beginIndex, int endIndex) {
		String substring = value.substring(beginIndex, endIndex);
		return (substring.length() > 0) ? Long.parseLong(substring) : -1;
	}

	/**
	 * Copy the given byte range of the given input to the given output.
	 *
	 * @param input
	 *            The input to copy the given range to the given output for.
	 * @param output
	 *            The output to copy the given range from the given input for.
	 * @param start
	 *            Start of the byte range.
	 * @param length
	 *            Length of the byte range.
	 * @throws IOException
	 *             If something fails at I/O level.
	 */
	private static void copy(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;

		if (input.length() == length) {
			// Write full range.
			while ((read = input.read(buffer)) > 0) {
				ByteArrayOutputStream bais = new ByteArrayOutputStream((int)length);
				bais.write(buffer, 0, read);
//				output.write(buffer, 0, read);
			}
		} else {
			// Write partial range.
			input.seek(start);
			long toRead = length;

			while ((read = input.read(buffer)) > 0) {
				if ((toRead -= read) > 0) {
					ByteArrayOutputStream bais = new ByteArrayOutputStream((int)length);
					bais.write(buffer, 0, read);
//					output.write(buffer, 0, read);
				} else {
					ByteArrayOutputStream bais = new ByteArrayOutputStream((int)length);
//					ByteArrayOutputStream bais = new ByteArrayOutputStream((int)length);
					bais.write(buffer, 0, (int)toRead + read);
					break;
				}
			}
		}
	}

	/**
	 * Copy the given byte range of the given input to the given output.
	 *
	 * @param input
	 *            The input to copy the given range to the given output for.
	 * @param output
	 *            The output to copy the given range from the given input for.
	 * @param start
	 *            Start of the byte range.
	 * @param length
	 *            Length of the byte range.
	 * @throws IOException
	 *             If something fails at I/O level.
	 */
	private static void copyFirstVersion(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;


		if (input.length() == length) {

			ByteArrayInputStream bai = new ByteArrayInputStream(buffer);
			// Write full range.
			while ((read = bai.read(buffer)) > 0) {
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
//				bai.read(buffer, 0, (int) length);
				bao.write(buffer, 0,(int) length);
//				output.write(buffer, 0, read);
			}
		} else {
			// Write partial range.
//			input.seek(start);
			long toRead = length;
//
//			while ((read = input.read(buffer)) > 0) {
//				if ((toRead -= read) > 0) {
//					output.write(buffer, 0, read);
//				} else {
//					output.write(buffer, 0, (int) toRead + read);
//					break;
//				}
//			}

			ByteArrayInputStream bai = new ByteArrayInputStream(buffer);
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			while ((read = bai.read(buffer)) > 0) {
				if((toRead -= read) > 0) {
					bao.write(buffer, 0, read);
				} else {
					bao.write(buffer,0,(int)toRead + read);
					break;
				}

				// bai.read(buffer, 0, (int) length);

			}
		}
	}

	/**
	 * Copy the given byte range of the given input to the given output.
	 *
	 * @param input
	 *            The input to copy the given range to the given output for.
	 * @param output
	 *            The output to copy the given range from the given input for.
	 * @param start
	 *            Start of the byte range.
	 * @param length
	 *            Length of the byte range.
	 * @throws IOException
	 *             If something fails at I/O level.
	 */
	private static void copyFirstVersion2(RandomAccessFile input, OutputStream output, long start, long length)
			throws IOException {

		// byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;
		ByteBuffer buffer = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
		FileChannel fch = input.getChannel();


		if (input.length() == length) {
			while (true) {
				buffer.clear();
				if (fch.read(buffer) == -1) {
					break;
				}
			}
			buffer.flip();
			output.write(buffer.get());
			// Write full range.
			// while ((read = input.read(buffer)) > 0) {
			// output.write(buffer, 0, read);
			// }
//			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, 0, length);
//			while (mapbuf.position() < mapbuf.capacity())
//				mapbuf.get();

		} else {
			// Write partial range.
			// input.seek(start);
			// long toRead = length;
			//
			// while ((read = input.read(buffer)) > 0) {
			// if ((toRead -= read) > 0) {
			// output.write(buffer, 0, read);
			// } else {
			// output.write(buffer, 0, (int) toRead + read);
			// break;
			// }
			// }

			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, start, length);
			while (mapbuf.position() < mapbuf.capacity())
				mapbuf.get();
		}
	}
}
