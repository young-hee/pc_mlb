package com.plgrim.ncp.framework.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

@Slf4j
public class NcpCryptoStandalone {

	private String KEY;

	private static final int SALT_COUNT = 20;
	private static final String ALGORITHM = "PBEWithMD5AndDES";

	public NcpCryptoStandalone() {
		super();
	}

	public NcpCryptoStandalone(String key) {
		this();
		this.KEY = key;
	}

	/**
	 * 암호화 시 특수문자(# / +  / & / % / 공백) replace 처리 안할 경우
	 * replace X (기존 모듈 적용)
	 * @param text
	 * @param salt
	 * @return
	 */
	public String encryptBase64(String text, String salt) {
		return encryptBase64(text, salt, false);
	}

	/**
	 * 암호화 시 특수문자(# / +  / & / % / 공백) replace 처리
	 * @param text
	 * @param salt
	 * @param replaceFlag
	 * @return
	 */
	public String encryptBase64(String text, String salt, boolean replaceFlag) {
		if( text == null ) return text;
		try {
			byte[] data = encryptCipher(salt).doFinal(text.getBytes("UTF-8"));
			if(replaceFlag){
				return encodeTextReplace(new String(Base64.encodeBase64(data, false, false), "UTF-8"));
			} else {
				return new String(Base64.encodeBase64(data, false, false), "UTF-8");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 복호화 시 특수문자(# / +  / & / % / 공백) replace 안할 경우
	 * replace X (기존 모듈 적용)
	 * @param base64encodedStr
	 * @param salt
	 * @return
	 */
	public String decryptBase64(String base64encodedStr, String salt) {
		return this.decryptBase64(base64encodedStr, salt, false);
	}

	/**
	 * 복호화 시 특수문자(# / +  / & / % / 공백) reaplce
	 * @param base64encodedStr
	 * @param salt
	 * @param replaceFlag
	 * @return
	 */
	public String decryptBase64(String base64encodedStr, String salt, boolean replaceFlag) {
		if (base64encodedStr == null) return base64encodedStr;
		try {
			String str = replaceFlag ? decodeTextReplace(base64encodedStr) : base64encodedStr;
			byte[] base64decoded = Base64.decodeBase64(str);
			byte[] decryptedBytes = decryptCipher(salt).doFinal(base64decoded);
			return new String(decryptedBytes, "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String encryptBase32(String text, String salt) {
		if( text == null ) return text;
		try {
			byte[] data = encryptCipher(salt).doFinal(text.getBytes("UTF-8"));
			return new String(new Base32().encode(data), "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String decryptBase32(String base32encodedStr, String salt) {
		if( base32encodedStr == null ) return base32encodedStr;
		try {
			byte[] base32decoded = new Base32().decode(base32encodedStr);
			byte[] decryptedBytes = decryptCipher(salt).doFinal(base32decoded);
			return new String(decryptedBytes, "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}



	/**
	 * Object 암호화
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public void encryptObj(Object source, Object target, boolean replaceFlag, String encryptSalt) throws Exception {
		String errPropNm = "";
		try {

			if (source != null && target != null) {
				Class<?> actualEditable = target.getClass();
				PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

				for (PropertyDescriptor targetPd : targetPds) {
					Method writeMethod = targetPd.getWriteMethod();

					if (writeMethod != null) {
						errPropNm = writeMethod.getName();
						PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());

						if (sourcePd != null) {
							Method readMethod = sourcePd.getReadMethod();

							if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
								if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
									readMethod.setAccessible(true);
								}

								Object value = readMethod.invoke(source);
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}

								if (value != null) {
									if (value instanceof List) {
										List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
										List<Map<String,Object>> readList = (List<Map<String,Object>>) value;
										for(Map<String,Object> tmp : readList) {
											Iterator ir = tmp.keySet().iterator();
											while (ir.hasNext()) {
												String mapKey = (String) ir.next();
												tmp.put(mapKey, this.encryptBase64( tmp.get(mapKey).toString(), encryptSalt, replaceFlag));
											}
											tmpList.add(tmp);
										}
										writeMethod.invoke(target, tmpList);
									} else if (value instanceof Map) {
										Map<String, Object> tmp = (Map<String, Object>) value;
										Iterator ir = tmp.keySet().iterator();
										while (ir.hasNext()) {
											String mapKey = (String) ir.next();
											tmp.put(mapKey, this.encryptBase64(tmp.get(mapKey).toString(), encryptSalt, replaceFlag));
										}
										writeMethod.invoke(target, tmp);

									}else if (value instanceof String) {
										writeMethod.invoke(target, this.encryptBase64(value.toString(), encryptSalt, replaceFlag));
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e) {
			log.error(">>>>>>>>>> encryptObj : ["+errPropNm+"] : "+ e.getMessage());
			throw e;
		}
	}

	/**
	 * Object 복호화
	 * @param source
	 * @param target
	 * @param encryptSalt
	 * @throws Exception
	 */
	public void decryptObj(Object source, Object target, boolean replaceFlag, String encryptSalt) throws Exception {
		String errPropNm = "";
		try {
			if (source != null && target != null) {
				Class<?> actualEditable = target.getClass();
				PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

				for (PropertyDescriptor targetPd : targetPds) {
					Method writeMethod = targetPd.getWriteMethod();
					if (writeMethod != null) {
						errPropNm = writeMethod.getName();
						PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
						if (sourcePd != null) {
							Method readMethod = sourcePd.getReadMethod();

							if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {

								if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
									readMethod.setAccessible(true);
								}

								Object value = readMethod.invoke(source);
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}

								if (value != null) {
									if (value instanceof List) {
										List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
										List<Map<String, Object>> readList = (List<Map<String, Object>>) value;
										for (Map<String, Object> tmp : readList) {
											Iterator ir = tmp.keySet().iterator();
											while (ir.hasNext()) {
												String mapKey = (String) ir.next();
												tmp.put(mapKey, this.decryptBase64(tmp.get(mapKey).toString(), encryptSalt, replaceFlag));
											}
											tmpList.add(tmp);
										}
										writeMethod.invoke(target, tmpList);

									} else if (value instanceof Map) {
										Map<String, Object> tmp = (Map<String, Object>) value;
										Iterator ir = tmp.keySet().iterator();
										while (ir.hasNext()) {
											String mapKey = (String) ir.next();
											tmp.put(mapKey, this.decryptBase64(tmp.get(mapKey).toString(), encryptSalt, replaceFlag));
										}
										writeMethod.invoke(target, tmp);

									} else if (value instanceof String) {
										writeMethod.invoke(target, this.decryptBase64(value.toString(), encryptSalt, replaceFlag));
									} else {
										writeMethod.invoke(target, value);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(">>>>>>>>>> decryptObj : ["+errPropNm+"] : "+ e.getMessage());
		}
	}


	/**
	 * 특수문자 인코딩 문제로 인하여 # / +  / & / % / 공백 문자 치환
	 * @param text
	 * @return
	 */
	private String encodeTextReplace(String text) {
		String[] findList = {"#", "+", "&", " ", "%"};
		String[] replList = {"%23", "%2B", "%26", "%20", "%25"};

		return StringUtils.replaceEach(text, findList, replList);
	}

	/**
	 * 복호화전 치환된 # / +  / & / % / 공백 문자 재 치환
	 * @param text
	 * @return
	 */
	private String decodeTextReplace(String text) {
		String[] findList = {"%23", "%2B", "%26", "%20", "%25"};
		String[] replList = {"#", "+", "&", " ", "%"};

		return StringUtils.replaceEach(text, findList, replList);
	}

	private Cipher encryptCipher(String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException {
		return initCipher(Cipher.ENCRYPT_MODE, salt);
	}

	private Cipher decryptCipher(String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException {
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
