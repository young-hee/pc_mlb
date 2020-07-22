package com.plgrim.ncp.framework.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CryptoUtilTest {

	private NCPCrypto crypto;

	@Before
	public void setUp() throws Exception {
		crypto = new NCPCrypto();
		crypto.setKEY("plgrimTOP");
		crypto.setDEFAULT_SALT("plgrimTOP");
		crypto.afterPropertiesSet();
	}

	@Test
	public void test() throws Exception {
		String src = "MBR2123123123,1231232143";
		String encrypted = crypto.encryptBase64(src, "New Salty");

		String decrypted = crypto.decryptBase64(encrypted, "New Salty");
		assertNotSame(encrypted, decrypted);
		System.out.println(encrypted);
		System.out.println(decrypted);

		assertEquals(src, decrypted);

		CryptoUtil.theCypto = crypto;
		assertEquals(encrypted, CryptoUtil.encryptBase64(src, "New Salty"));
	}

	@Test
	public void notContainsLineSeparator() {
		String src = "MBR2123123123,1231232143";
		String encrypted = crypto.encryptBase64(src);

		assertFalse(encrypted.contains("\r"));
		assertFalse(encrypted.contains("\n"));
	}

	@Test
	public void diffWhenSaltIsDiff() throws Exception {
		String src = "Hello World";
		String encrypted1 = crypto.encryptBase64(src, "New Salty1");
		String encrypted2 = crypto.encryptBase64(src, "New Salty2");

		assertNotSame(encrypted1, encrypted2);
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void invalidKeyLength() throws Exception {
//		NCPCrypto crypto = new NCPCrypto();
//		crypto.setKEY("plgrimTOP1");
//		crypto.afterPropertiesSet();
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void invalidKeyNull() throws Exception {
//		NCPCrypto crypto = new NCPCrypto();
//		crypto.afterPropertiesSet();
//	}

}
