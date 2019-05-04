package com.example.demo.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileOperationUtils {

	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.

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
					//reReadから余分に引いたread文をもとに戻して書き込む
					output.write(buffer, 0, (int) toRead + read);
					break;
				}
			}
		}
	}



	public  static void copyFast(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {

		FileChannel fch = input.getChannel();

		if (input.length() == length) {
			// Write full range.
			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, 0,length
					);
			while(mapbuf.position() < mapbuf.capacity()) {
				output.write(mapbuf.get());

			}
		} else {
			// Write partial range.
			long toRead = length;
			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, 0,length);

			mapbuf.position((int) start).limit((int) (length));
			while(mapbuf.position() < mapbuf.capacity()) {
				output.write(mapbuf.get());

			}
		}
	}

	public static void copyFast2(RandomAccessFile input, OutputStream output, long start, long length)
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
//			output.write(mapbuf.get());

		} else {

			MappedByteBuffer mapbuf = fch.map(MapMode.READ_ONLY, start, length);
			while (mapbuf.position() < mapbuf.capacity())
			output.write(mapbuf.get());
		}
	}
}
