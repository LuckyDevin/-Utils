package com.ccx.credit.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @create 2016/04/18
 * 
 * @author wbh
 */

public class JsonUtils {

	private JsonUtils() {
	}

	/**
	 *生成Json字符串
	 * @param object
	 * @return String
	 */
	public static String createJson(Object object){
		return JSON.toJSONString(object);
	}
	/**
	 * 获取String
	 * @param json
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getStringVal(String json,String key) {
		try{
			JSONObject jsonObject = JSONObject.parseObject(json);
			return jsonObject.getString(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	
	/**
	 * 获取int
	 * @param json
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static int getIntVal(String json,String key) throws Exception{
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject.getInteger(key);
	}

	/**
	 * 获取long
	 * @param json
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Long getLongVal(String json,String key) throws Exception{
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject.getLong(key);
	}

	/**
	 * 功能描述：把JSON数据转换成普通字符串列表
	 *
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<String> getStringList(String jsonData) throws Exception {
		return JSON.parseArray(jsonData, String.class);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 *
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> T getSingleBean(String jsonData, Class<T> clazz)
			throws Exception {
		return JSON.parseObject(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 *
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> List<T> getBeanList(String jsonData, Class<T> clazz)
			throws Exception {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 *
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<Map<String, Object>> getBeanMapList(String jsonData)
			throws Exception {
		return JSON.parseObject(jsonData,
				new TypeReference<List<Map<String, Object>>>() {
				});
	}

	/**
	 * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
	 * @param result
	 * @return
	 */
	public static String dealResponseResult(String result) {
		result = JSONObject.toJSONString(result,
				SerializerFeature.WriteClassName,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteEnumUsingToString,
				SerializerFeature.WriteSlashAsSpecial,
				SerializerFeature.WriteTabAsSpecial);
		return result;
	}
	
	
	
	 
}
