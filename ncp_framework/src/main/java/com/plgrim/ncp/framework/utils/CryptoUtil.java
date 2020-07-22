package com.plgrim.ncp.framework.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

@Component
public class CryptoUtil implements InitializingBean {
	@Autowired
	NCPCrypto crypto;

	static NCPCrypto theCypto;

	public static String encryptBase64(String text) {
		return theCypto.encryptBase64(text);
	}

	public static String decryptBase64(String base64encodedStr) {
		return theCypto.decryptBase64(base64encodedStr);
	}

	public static String encryptBase64(String text, String salt) {
		return theCypto.encryptBase64(text, salt);
	}

	public static String decryptBase64(String base64encodedStr, String salt) {
		return theCypto.decryptBase64(base64encodedStr, salt);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CryptoUtil.theCypto = crypto;
	}

	public static String encodeBase64String(byte[] text, char[] salt) {
		// return new BASE64Encoder().encode(encode(text, salt));
		return null;
	}

	public static String encodeBase64String(String text, char[] salt) {
		return encodeBase64String(text.getBytes(), salt);
	}

	public static String encodeBase64String(String text, String salt) {
		return encodeBase64String(text, salt.toCharArray());
	}

	public static byte[] encryptRsaOAEPWithSHA1AndMGF1Padding(PublicKey publicKey, byte[] src) {
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(src);
		} catch (GeneralSecurityException e) {
		    e.printStackTrace();
		    return null;
		}
	}

	//public static byte[] ivBytes = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};

	/**
	 * 입력된 key를 통하여 byte[] src를 복호화 한다.
	 *
	 * @param key - sessionkey 32byte 와 iv 16byte 가  조합된 48자리 String 문자열이 와야 한다. (String은 ascii 문자열만 포함되어야 한다.)
	 * @param src - 복호화 대상 byte []
	 * @return 복호화된 byte[]
	 */
	public static byte[] decryptAESPKCS7Padding(String key, byte[] src) throws NoSuchAlgorithmException,
										javax.crypto.NoSuchPaddingException, UnsupportedEncodingException,
										InvalidKeyException, InvalidAlgorithmParameterException,
										IllegalBlockSizeException, BadPaddingException {
		String descyptKey = key.substring(0,32);
		String ivStr = key.substring(32, 48);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes());
		SecretKeySpec newKey = new SecretKeySpec(descyptKey.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
		return cipher.doFinal(src);
	}

	public static byte[] encryptAESPKCS7Pading(String key, byte[] src) throws UnsupportedEncodingException, NoSuchAlgorithmException, javax.crypto.NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		String descyptKey = key.substring(0,32);
		String ivStr = key.substring(32, 48);

		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes());
		SecretKeySpec newKey = new SecretKeySpec(descyptKey.getBytes("UTF-8"), "AES");
		Cipher cipher = null;
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
		return cipher.doFinal(src);
	}

//	public static void main(String...strings) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, javax.crypto.NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
//		String temp = RandomStringUtils.randomAscii(48);
//		String plainTxt = "안녕하세요. 한글입니다.";
//		System.out.println(plainTxt);
//		byte [] encBytes = CryptoUtil.encryptAESPKCS7Pading(temp, plainTxt.getBytes() );
//		System.out.println( encBytes.toString() );
//
//
//		String key = "27e7vRdU5ZeZQ1ZSIL9cf0wE8VtxFAxxwgfGVDOXj68NfKQp";
//		String encTxt = "ARc0RHY/i+CRqNxLpbma8FSpxClUZLdMhxVUy98QnecZiI1UCQD+fZ0G/GttnWrYWxhlDBZs6A9meEPmhEUzODskbJgVoOe7wTJa/6N3TiigmPLeXLWAIDMDGIu5xiGjwM+unslwl+WwDE3sCT7ED3jwF9engupBdnQNifoMG29Bj/kNgkeymOpuEymmPSbcEv4692h2CyE9jvnWa/Fj3LE8IwkbX+zKXZLmWTZlOLg1he2mR0Fz8kezLHcy4mVxOLKgf/MOLDU7J5TnO/LRM9IN/dtBUcLjeyNbrAOzK9PbrghFivLkx46AYqhp7tbltilZzQcGokUha2N8MayPTrxdJzdq9+Bec4KlAmiazeCS3QAHKOlwgBGJnml+FjxXQBSsWKKQuCmjlU6WrmOrEpYKZkkqSoCKqd0AJSUFZDBt9/w6ratM5H0PRkdsPofYHLWu3kU1+J0qkUmAkmvfOLp2zJgX3gLDnCT3VydgLySjdFrAb9q7J0IjktLP32tPmqrYGcBUsp5yTvYCUD7xVi/4X3+uSKrGWHyiRZJsevCYt24/BJkOxDC04DZNhAmopUKTdxlhBsqAb9wg8AuJs/a9adxiUvO6+Qq25m1CHMKbYrzmbZ7vBRgaz8ga5/4yQo04cWqDsk45hvlG8GSxMius4vc6WWomtPGEdZt/zr8Q7wasZro/w7NFhf3cQ0jPEI7AzSIsvAk8f1OQA5E8rTTDaHxD/xaROJKtpiwZ0La4u62n9jnEJ/ZgSc1E2l55biBpSW8JEzNyay34IoDlSnLn8T2qUOspw7SqF4LGObG/T1A72RvJBsJcq6D7qKjyf/h4BUHcRakxyCAE0kNEb+dIy5RXhY6X0QCWoenZfNG7GdBEt3fhnFI7Kozzgf6fpGNr2/twDgAinGHeJ94m9Dkl5zcNqdC1Rcvp6uZjD0/m0agouiRrJpIR7OFeggVX";
//
//		byte [] decBytes = CryptoUtil.decryptAESPKCS7Padding(key, Base64.decodeBase64(encTxt) );
//		System.out.println( decBytes.toString() );
//
//		System.out.println( new String (decBytes));
//
//	}



}
