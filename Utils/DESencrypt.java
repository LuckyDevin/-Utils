package com.ccx.credit.util;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DESencrypt {
	
	private static String SECRET_KEY = "ccx_2288";

	/**
	 * 加密
	 * @param key
	 * @param value	
	 * @return
	 */
	public static String encryptECB(String key, String value) {
		try {
			SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] binaryData = cipher.doFinal(value.getBytes("UTF-8"));
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(binaryData);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			System.out.println("Invalid DES key, not encrypting");
			return null;
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Error in encryption, not encrypting");
			return null;
		}
	}

	/**
	 * 解密
	 * @param key
	 * @param value
	 * @return
	 */
	public static String decryptECB(String key, String value) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] binaryValue = decoder.decodeBuffer(value);
			SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] data = cipher.doFinal(binaryValue);
			return new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String key = SECRET_KEY;
//		String value = "/NYffyZx5ycj1FcWgGE5rE4da1AH8x/ym7W2Hxh+X0QKnt4uivtOkA==";
		String value = "3168fc3ce8e64c799b7c04fe86058344";
//		String value = "投保信息-testcrypt";
		String encryptText = encryptECB(key, value);
		String originData = decryptECB(key, encryptText);
		System.out.println(encryptText);
		System.out.println(originData);
	}
	
	
	
}

