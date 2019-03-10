package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileOperationUtils {

	/** デフォルトのバッファサイズ */
	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.

	/**
	 * RandomAccessFileの内容をOutputStreamに書き込む。開始位置start、長さlength
	 * @param input
	 * @param output
	 * @param start
	 * @param length
	 * @throws IOException
	 */
	public  static void copy(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;

		if (input.length() == length) {
			// Write full range.
			while ((read = input.read(buffer)) > 0) {
				output.write(buffer, 0, read);
			}
		} else {
			// Write partial range.
			//開始位置までシーク
			input.seek(start);
			//読み込まなければならないバイト数(全体のバイト数-すでに読み込んだバイト数)
			long toRead = length;

			while ((read = input.read(buffer)) > 0) {
				if ((toRead -= read) > 0) {
					output.write(buffer, 0, read);
				} else {
					output.write(buffer, 0, (int) toRead + read);
					break;
				}
			}
		}
	}



	public  static void copyFast(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

		FileChannel fch = input.getChannel();
		fch.force(false);
		if (input.length() == length) {
			// Write full range.
			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, 0,length
					);
			while(mapbuf.position() < mapbuf.capacity()) {
				output.write(mapbuf.get());

			}
//			while ((read = input.read(buffer)) > 0) {
//				output.write(buffer, 0, read);
//			}
		} else {
			// Write partial range.
//			input.seek(start);
			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, 0,length);

//			mapbuf.position((int) start).limit((int) (length -start));
			mapbuf.position((int)start).limit((int) length);
			while(mapbuf.position() < mapbuf.capacity()) {
				output.write(mapbuf.get());

//			while ((read = input.read(buffer)) > 0) {
//				if ((toRead -= read) > 0) {
//					MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, start,read
//							);
//					while(mapbuf.position() < mapbuf.capacity()) {
//						output.write(mapbuf.get());
//
//					}
//				} else {
////					output.write(buffer, 0, (int) toRead + read);
//					MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, start,(int)toRead + read
//							);
//					while(mapbuf.position() < mapbuf.capacity()) {
//						output.write(mapbuf.get());
//
//					}
//					break;
//				}
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
	public static void copy2(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

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
