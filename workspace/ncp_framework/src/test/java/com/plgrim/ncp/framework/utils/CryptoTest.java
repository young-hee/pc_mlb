package com.plgrim.ncp.framework.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoTest {

	@Test
	public void test() throws Exception {
		String originalText = "plain text";
		String key = "ncp_apppush";
		Crypto crypto = new Crypto(key);

		String encryptedStr = crypto.encrypt(originalText);
		assertEquals("OqxoNq1m4AGHwbKmeLLjbw==", encryptedStr);

		String decryptedStr = crypto.decrypt(encryptedStr);
		assertEquals(originalText, decryptedStr);
	}

}
