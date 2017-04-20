package com.ccx.credit.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

/**
 * 字符串处理工具类 依赖 google guava
 */
public final class StringUtil extends StringUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	private StringUtil() {
		throw new Error("工具类不能实例化！");
	}

	/**
	 * 判断字符串是否为""或null
	 */
	public static boolean isNullOrEmpty(String msg) {
		return Strings.isNullOrEmpty(msg);
	}

	/**
	 * 判断字符串是否为空白字符串（包括""）或null
	 */
	public static boolean isNullOrBlank(String msg) {
		boolean flag = true;
		if (Strings.isNullOrEmpty(msg)) {
			return flag;
		} else {
			for (int i = 0, length = msg.length(); i < length; i++) {
				if (!Character.isWhitespace(msg.charAt(i))) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * object to String， null返回""
	 */
	public static String objectToString(Object obj) {
		String result = "";
		if (obj != null) {
			result = obj.toString();
		}
		return result;
	}

	/**
	 * 如果一个字符串为null返回"",避免空指针问题
	 */
	public static String nullToEmpty(String msg) {
		return Strings.nullToEmpty(msg);
	}
	
	/**
	 * Blob类型 转 String类型
	 * @param blob
	 * @return
	 */
	public static String blobToString(Blob blob){
		String result = "";
		if ( blob != null ){
			InputStream is;
			try {
				is = blob.getBinaryStream();
				ByteArrayInputStream bais = (ByteArrayInputStream)is;
				byte[] byte_data = new byte[bais.available()];
				bais.read(byte_data,0,byte_data.length);
				result = new String(byte_data,"utf-8");
				is.close();
			} catch (SQLException e) {
				logger.info("Blob转化为String过程异常",e);
			} catch (UnsupportedEncodingException e) {
				logger.info("Blob转化为String过程异常",e);
			} catch (IOException e) {
				logger.info("Blob转化为String过程异常",e);
			}
		}
		return result;
	}
	
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param src
	 *            需判断的字符串
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isNotEmpty(String src) {
		return !isEmpty(src);
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param src
	 *            需判断的对象
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isNotEmpty(Object src) {
		return !isEmpty(src);
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param collection
	 *            需判断的集合
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 *            需判断的Map
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param array
	 *            需判断的数组
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	
	
	/**
	 * 字符串类型为空的判断,为空返回true
	 * 
	 * @param src
	 *            需判断的字符串
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isEmpty(String src) {
		return src == null || src.length() == 0;
	}

	/**
	 * 对象为空的判断
	 * 
	 * @param src
	 *            需判断的对象
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isEmpty(Object src) {
		if (src == null) {
			return true;
		} else if (src instanceof String) {
			return isEmpty((String) src);
		} else if (src instanceof Map) {
			return isEmpty((Map<?, ?>) src);
		} else if (src instanceof Collection) {
			return isEmpty((Collection<?>) src);
		} else if (src.getClass().isArray()) {
			return isEmpty((Object[]) src);
		} else {
			return src == null;
		}
	}

	/**
	 * 集合为空的判断
	 * 
	 * @param src
	 *            需判断的集合
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * Map为空的判断
	 * 
	 * @param src
	 *            需判断的Map
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * 数组为空的判断
	 * 
	 * @param src
	 *            需判断的数组
	 * @return boolean
	 * @throws
	 * @exception
	 */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
	

	
	
	
	
	
	
	
	/**
	 * 科学计数法转换成正常计数
	 * @param scienceNum
	 * @return
	 */
	public static String scienceNumToNum(String scienceNum){
		if(null != scienceNum){
			BigDecimal db = new BigDecimal(scienceNum);
			String stringNum = db.toPlainString();
			return stringNum;
		}
		return "";
	}
	
	/**
	 * json文件内容转换成String
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String jsonFileToString(String filePath, String fieldName) {
		if(null != filePath){
//			filePath = filePath.replaceAll("mnt", "var");
			if (System.getProperty("os.name").indexOf("Windows") > -1) {
				filePath = "D:" + filePath;
			}
			String read = "";
			String readStr = "";
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
				while ((read = br.readLine()) != null) {
					readStr = readStr + read;
				}
				readStr = readStr.substring(0, readStr.length());
				if (null != readStr) {
					if(null != fieldName){
						JSONObject json = JSONObject.parseObject(readStr);
						if(null != json){
							String jsonString = json.getString(fieldName);
							if(jsonString != null){
								jsonString = jsonString.replaceAll("([<＜]{1,2})([\u4E00-\u9FA5+,，.。!！?？《》＜＞（）、\\-]{1,})([>＞]{1,2})", "【$2】")
														.replaceAll("<ahref=\"([\\w.:?]{1,})>", "")
														.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "")//替换xml不能识别 中非法字符
														.replaceAll("<w:p>.*?<w:t>*","<p>")
														.replaceAll("</w:t>.*?</w:p>*","</p>");//替换包含 WordXml 字符 
								
							}
							return jsonString;
						}
					}else{
						return readStr;
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return "";
		}
		return "";
	}
	
	public static String jsonFileToString(String filePath){
		if(null != filePath){
			if (System.getProperty("os.name").indexOf("Windows") > -1) {
				filePath = "D:" + filePath;
			}
			String read = "";
			String readStr = "";
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
				while ((read = br.readLine()) != null) {
					readStr = readStr + read;
				}
				readStr = readStr.substring(0, readStr.length());
				return readStr;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return "";
		}
		
		return "";
	}
	
	//读取项目内jsonFile
	public static String localJsonFileToString(String filePath){
		if(null != filePath){
			String read = "";
			String readStr = "";
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
				while ((read = br.readLine()) != null) {
					readStr = readStr + read;
				}
				readStr = readStr.substring(0, readStr.length());
				return readStr;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return "";
		}
		
		return "";
	}

	public static Map<String,Object> getAgeNameByCid(String cid){
		Map<String,Object> map = new HashMap<String, Object>();
		if(cid == null || cid.equals("")){
			map.put("sex", "--");
			map.put("age", "--");
			return map;
		}else{
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String sys_Year = format.format(date).substring(0, 4);
			String sys_Month = format.format(date).substring(5, 7);
			if(cid.length()==18){
				String cid_Year = cid.substring(6).substring(0, 4);
				String cid_Month = cid.substring(10).substring(0, 2);
				String sex ;
				if(Integer.parseInt(cid.substring(16).substring(0, 1))%2 == 0){
					sex = "女";
				}else{
					sex = "男";
				}
				int age = 0;
				if(Integer.parseInt(cid_Month)<=Integer.parseInt(sys_Month)){
					age = Integer.parseInt(sys_Year)-Integer.parseInt(cid_Year)+1;
				}else{
					age = Integer.parseInt(sys_Year)-Integer.parseInt(cid_Year);
				}
				map.put("sex", sex);
				map.put("age", age);
				
			}else if(cid.length()==15){
				String cid_Year = "19"+ cid.substring(6, 8);
				String cid_Month = cid.substring(8, 10);
				String sex;
				if(Integer.parseInt(cid.substring(14,15))%2 == 0){
					sex ="女";
				}else{
					sex = "男";
				}
				int age = 0;
				if(Integer.parseInt(cid_Month)<=Integer.parseInt(sys_Month)){
					age = Integer.parseInt(sys_Year)-Integer.parseInt(cid_Year)+1;
				}else{
					age = Integer.parseInt(sys_Year)-Integer.parseInt(cid_Year);
				}
				map.put("sex", sex);
				map.put("age", age);
			}else{
				map.put("sex", "--");
				map.put("age", "--");
			}
			return map;
		}
	}
	
	private static final DecimalFormat DF = new DecimalFormat("#,##0.00");
	private static final Pattern notNumberParttern = Pattern.compile("([a-zA-Z\u4E00-\u9FA5]{1,})");
	
	/**
	 * 格式化文本数字为(#,##0.00) 
	 * 12000-->12,000.00 
	 * @param text
	 * @return
	 */
	public static String formatTextNumber(String text){
		
		if(null != text && !text.equals("")){
			Matcher m = notNumberParttern.matcher(text);
			text = text.replaceAll(",", "");
			boolean isFormat = m.find();
			if(isFormat){
				text = "--";
			}else{
				text = DF.format(Double.valueOf(text));	
			}
		}else{
			text = "--";
		}
		return text;
	}
	
//	public static void main(String[] args) {
//		//重新转成json字符串，｛“name”:"张三","age":"20","xb":“男”,"zzmm":"newValue"｝
//		String json = "{\"identityDetail\":{\"address\":\"望京东园\",\"birthPlace\":\"辽宁省沈阳市\",\"birthday\":\"1980-01-01\",\"company\":\"外企服务总公司\",\"edu\":\"研究生\",\"formerName\":\"\",\"id_number\":\"210122198001015415\",\"maritalStatus\":\"已婚\",\"name\":\"姚明\",\"nation\":\"汉族\",\"originPlace\":\"辽宁省沈阳市\",\"sex\":\"男性\"},\"photo\":\"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\"}";
//		Map<String,String> value = JSON.parseObject(json,Map.class);
//		value.put("photo","XXXXXXXXXXXX");
//		value.put("photoa","123");
//		System.out.println(JSON.toJSONString(value));
//		System.out.println(json);
//		
//	}
	  /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param path 图片路径
     * @return base64字符串
     */
    public static String imageToBase64(String path) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return org.apache.commons.codec.binary.Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
    }
	/**
	 * 时间格式化 、20120909-->2012-09-09
	 * 20120909 000000-->2012-09-09
	 * @param time
	 * @return
	 */
	public static String formateTextTime(String time) {
		if(StringUtil.isNotEmpty(time)){
			if (time.length()>10) {
				time = time.substring(0,10);
			}else if(time.length()==8){
				time = time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8);
			}
		}else{
			time = "";
		}
		
		return time;
	}
	
	public static void main(String[] args) {
//		String t = "<p>索同仁医院时，第一个搜索结果为：北京<ahref=\"http:www.baidu.c0m?>www.baidu.c0m最好<精神科+神经科>医院www.bjjyzyy.com/tel:010-83980777，<中华人民共和国民事诉讼法></p><你>";
//		String t = "《最高人民法院<关于审理，注册,商标、企业名-称与在先。权利冲突.的民事纠纷?案件若干问题的规定？>》";
//		System.out.println("替换之前：" + t);
//		t = t.replaceAll("<([\u4E00-\u9FA5+,，.。!！?？、\\-]{1,})>", "“$1”");
//		System.out.println("替换之前：" + t);
//		t = t.replaceAll("<ahref=\"([\\w.:?]{1,})>", "");
//		System.out.println("替换之前：" + t);
		
		/*String cid = "220102199212215012";
		Map<String, Object> map = new HashMap<>();
		map = StringUtil.getAgeNameByCid(cid);
		System.out.println(map.get("sex"));*/ 
		
		String ss = "啊啊第二百零七条、<<最高人民法院关于适用＜中华人民共和国担保法＞若干问题的解释>>第六十七条之规定，根据<最高人民法院关于适用《中华人民共和国民事诉讼法》若干问题的意见＞第66条的规定，依据《最高院关于适用<中华人民共和国公司法若干问题的规定（三）>》第四条的规定，";
		System.out.println(ss);
		ss = ss.replaceAll("([<＜]{1,2})([\u4E00-\u9FA5+,，.。!！?？《》＜＞（）、\\-]{1,})([>＞]{1,2})", "【$2】");
		System.out.println(ss);
//		ss = ss.replaceAll("<([\u4E00-\u9FA5+,，.。!！?？《》、\\-]{1,})＞", "“$1”");
//		System.out.println(ss);
//		ss = ss.replaceAll("<ahref=\"([\\w.:?]{1,})>", "");
//		System.out.println(ss);
		
		
	}
	
	
	
	
}
