package com.ccx.credit.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccx.credit.assess.controller.CreditQueryController;

/**
 * 接口加密规则
 * 
 * @author wbh
 * 
 */
public class InterfaceEncryptionRules {
	
	/*public static void main(String[] args) throws UnsupportedEncodingException {
		
		//reqTid sn sign cid name mobile card
		String url = "http://192.168.10.25:9080/data-service/credit/report/part/identity";
		long reqId = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb  =new StringBuffer();
		sb.append(url);
		sb.append("?account=zhangyuA");
		sb.append("&cid=210122198001015415");
		sb.append("&reqTid=");
		sb.append(reqId);
		sb.append("&sign=");
		map.put("account", "zhangyuA");
		map.put("cid", "210122198001015415");
//		map.put("reqTid",reqId);
		sb.append(InterfaceEncryptionRules.getSecretStr(map));
		System.out.println(sb.toString());
		System.out.println(HttpClientUtil.sendGetRequest(sb.toString(), "UTF-8"));
		
		
		
//		String url = "http://192.168.10.24/data-service/riskinfo/batch/classify";
//		Map<String, Object> map = new HashMap<String, Object>();
//		StringBuffer sb  =new StringBuffer();
//		sb.append(url);
//		sb.append("?account=zhangyuA");
//		map.put("account", "zhangyuA");
//		sb.append("&cid=3205821986****2315");
//		sb.append("&name=");
//		sb.append(URLEncoder.encode("王启华", "utf-8"));
//		sb.append("&type=1");
//		map.put("type", "1");
//		sb.append("&cat=103");
//		sb.append("&reqTid=21443543456676");
//		sb.append("&sign=");
//		sb.append(InterfaceEncryptionRules.getSecretStr(map));
//		System.out.println(sb.toString());
		
		
		
	}
	*/
	
	
	/**
	 * 获取验签
	 * @param map
	 * @return
	 */
	public static String getSecretStr(Map<String, Object> map){
		Map<String, Object> aMap = sortMapByValues(map);
		String tmp = "";
		for (Entry<String, Object> entry : aMap.entrySet()) {
			tmp += entry.getKey() + entry.getValue();
		}
		System.out.println("验签字符：" + tmp);
		tmp = MD5.encryption(tmp).toUpperCase();
		System.out.println("验签生成：" + tmp);
		return tmp;
	}
	
	

	/**
	 * 验签参数排序
	 * 
	 * @param aMap
	 */
	private static Map<String, Object> sortMapByValues(Map<String, Object> aMap) {

		Set<Entry<String, Object>> mapEntries = aMap.entrySet();

		// used linked list to sort, because insertion of elements in linked
		// list is faster than an array list.
		List<Entry<String, Object>> aList = new LinkedList<Entry<String, Object>>(mapEntries);

		// sorting the List
		Collections.sort(aList, new Comparator<Entry<String, Object>>() {
			@Override
			public int compare(Entry<String, Object> ele1,
					Entry<String, Object> ele2) {

				return ele1.getKey().compareTo(ele2.getKey());
			}
		});

		// Storing the list into Linked HashMap to preserve the order of
		// insertion.
		Map<String, Object> aMap2 = new LinkedHashMap<String, Object>();
		for (Entry<String, Object> entry : aList) {
			aMap2.put(entry.getKey(), entry.getValue());
		}
//		// printing values after soring of map
//		System.out.println("key " + " - " + "value");
//		for (Entry<String, Object> entry : aMap2.entrySet()) {
//			System.out.println(entry.getKey() + " - " + entry.getValue());
//		}

		return aMap2;
	}

}
