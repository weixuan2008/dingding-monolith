package com.hy.micro.service.dingding.utils;

import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashUtilsTest {
	private static final String plainText = "123456";

	@Test
	public void md5Test() throws NoSuchAlgorithmException {
		String result = HashUtils.hashWithMD5(plainText);

		String expectedVal = "e10adc3949ba59abbe56e057f20f883e";
		Assert.assertEquals(expectedVal, result);
		log.info("Hashed value is: {}", result);
	}

	@Test
	public void sha1Test() throws NoSuchAlgorithmException {
		String result = HashUtils.hashWithSHA1(plainText);

		String expectedVal = "7c4a8d09ca3762af61e59520943dc26494f8941b";
		Assert.assertEquals(expectedVal, result);
		log.info("Hashed value is: {}", result);
	}

	@Test
	public void sha256Test() throws NoSuchAlgorithmException {
		String result = HashUtils.hashWithSHA256(plainText);

		String expectedVal = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
		Assert.assertEquals(expectedVal, result);
		log.info("Hashed value is: {}", result);
	}

	@Test
	public void sha512Test() throws NoSuchAlgorithmException {
		String result = HashUtils.hashWithSHA512(plainText);

		String expectedVal = "ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413";
		Assert.assertEquals(expectedVal, result);
		log.info("Hashed value is: {}", result);
	}

}
