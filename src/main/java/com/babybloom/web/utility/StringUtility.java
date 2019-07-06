package com.babybloom.web.utility;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class StringUtility {
	private StringUtility() {
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 过滤掉UTF-8字符串中占4个字节的大字符(解决类似emoji表情 插入当前utf8编码的数据库报异常问题)
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			short b = bytes[i];
			if (b > 0) {
				buffer.put(bytes[i++]);
				continue;
			}

			b += 256; // 去掉符号位

			if (((b >> 5) ^ 0x6) == 0) {
				buffer.put(bytes, i, 2);
				i += 2;
			} else if (((b >> 4) ^ 0xE) == 0) {
				buffer.put(bytes, i, 3);
				i += 3;
			} else if (((b >> 3) ^ 0x1E) == 0) {
				i += 4;
			} else if (((b >> 2) ^ 0x3E) == 0) {
				i += 5;
			} else if (((b >> 1) ^ 0x7E) == 0) {
				i += 6;
			} else {
				buffer.put(bytes[i++]);
			}
		}
		buffer.flip();
		return new String(buffer.array(), "utf-8").trim();
	}
}
