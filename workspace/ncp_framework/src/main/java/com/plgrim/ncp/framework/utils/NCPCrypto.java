package com.plgrim.ncp.framework.utils;

import com.plgrim.ncp.framework.security.KeyProvider;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
@Data
public class NCPCrypto implements InitializingBean {
	@Value("${ncp_base.crypto.key}")
	private String KEY;
	@Value("${ncp_base.crypto.defaultSalt}")
	private String DEFAULT_SALT;

	@Autowired(required = false)
	KeyProvider keyProvider;

	private static final int SALT_COUNT = 20;
	private static final String ALGORITHM = "PBEWithMD5AndDES";

	@Override
	public void afterPropertiesSet() throws Exception {
		if(getKeyProvider() != null){
//			KEY = getKeyProvider().getKey();

			if (StringUtils.isEmpty(KEY) || KEY.length() % 8 != 0 || StringUtils.isEmpty(DEFAULT_SALT)) {
				throw new IllegalArgumentException("KEY is invalid");
			}
		}
	}

	public String encryptBase64(String text) {
		return encryptBase64(text, DEFAULT_SALT);
	}

	public String decryptBase64(String base64encodedStr) {
		return decryptBase64(base64encodedStr, DEFAULT_SALT);
	}

	public String encryptBase64(String text, String salt) {
		try {
			byte[] data = encryptCipher(salt).doFinal(text.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(data, false, false), "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String decryptBase64(String base64encodedStr, String salt) {
		try {
			byte[] base64decoded = Base64.decodeBase64(base64encodedStr);
			byte[] decryptedBytes = decryptCipher(salt).doFinal(base64decoded);
			return new String(decryptedBytes, "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Cipher encryptCipher(String salt) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		return initCipher(Cipher.ENCRYPT_MODE, salt);
	}

	private Cipher decryptCipher(String salt) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		return initCipher(Cipher.DECRYPT_MODE, salt);
	}

	private Cipher initCipher(int encryptMode, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(KEY.getBytes(), SALT_COUNT);
		PBEKeySpec pbeKeySpec = new PBEKeySpec(salt.toCharArray());
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey skey = keyFac.generateSecret(pbeKeySpec);
		Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
		pbeCipher.init(encryptMode, skey, pbeParamSpec);
		return pbeCipher;
	}

}