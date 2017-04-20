package com.ccx.credit.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MD5SIGN {

	public static void main(String[] args) {

		String account = "zhangyuA";
		String mname = "江阴高新区碧桂园置业有限公司";
		String midpid = "898320215200499";
		String regNo = "12345678-1";
		String name = "干杰";
		String mobile = "13800138000";
		String cid = "330184198708106210";
		String reqTid = "201612081512";

		Map<String, String> param = new HashMap<String, String>();
		param.put("account", account);
		param.put("mname", mname);
		param.put("midpid", midpid);
		param.put("regNo", regNo);
		param.put("name", name);
		param.put("mobile", mobile);
		param.put("reqTid", reqTid);
		param.put("cid", cid);

		buildRequestPara(param, "");

	}

	/**
	 * 生成要请求接口的签名字符串，签名结果与签名方式加入请求提交参数组中
	 * 
	 * @param sParaTemp
	 *            需要签名的参数
	 * @param private_key
	 *            私钥
	 * @return 签名字符串
	 */
	public static String buildRequestPara(Map<String, String> sParaTemp, String private_key) {
		// 除去数组中的空值和签名参数
		System.out.println("生成签名（开始）：" + sParaTemp);

		// 清洗参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		System.out.println("清洗后：" + sParaTemp);

		// 排序参数
		String prestr = createLinkString(sPara, private_key); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		System.out.println("排序后：" + prestr);

		// 生成签名
		String sign = buildRequestMysign(prestr);
		System.out.println("生成签名（结束）：" + sign);

		return sign;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @param private_key
	 *            私钥
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params, String private_key) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			prestr = prestr + key + value;
		}

		return prestr + private_key;
	}

	/**
	 * MD5生成签名
	 * 
	 * @param plainText
	 *            明文参数
	 * @return 32位密文
	 */
	public static String buildRequestMysign(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes("Utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

}
