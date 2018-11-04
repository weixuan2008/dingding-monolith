package com.hy.micro.service.dingding.utils;

import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;

/**
 * The help class for hash function
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
@Slf4j
public class HashUtils {
	public static String hashWithMD5(final String input) {
		return hash(input, "MD5");
	}

	public static String hashWithSHA1(final String input) {
		return hash(input, "SHA-1");
	}

	public static String hashWithSHA256(final String input) {
		return hash(input, "SHA-256");
	}

	public static String hashWithSHA512(final String input) {
		return hash(input, "SHA-512");
	}

	private synchronized static String hash(final String input, final String hashType) {
		String strResult = null;

		try {
			if (input != null && input.length() > 0) {
				MessageDigest messageDigest = MessageDigest.getInstance(hashType);

				messageDigest.update(input.getBytes());

				byte byteBuffer[] = messageDigest.digest();

				StringBuffer strHexString = new StringBuffer();

				for (int i = 0; i < byteBuffer.length; i++) {
					String hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				strResult = strHexString.toString();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return strResult;
	}
}
