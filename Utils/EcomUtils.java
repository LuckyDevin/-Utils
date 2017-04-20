package com.ccx.credit.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 电商消费画像json数据转化
 * @author sijincao
 *
 */
public class EcomUtils {
	public static Map<String,JSONArray> jsonConvertMap(JSONObject obj){
		Map<String,JSONArray> map = new HashMap<String,JSONArray>();
		
		JSONObject data = obj.getJSONObject("data");
		//年度统计
	    JSONObject esYear = data.getJSONObject("esYear");
	    if(esYear != null && !esYear.equals("")){
	    	JSONArray jname1 = new JSONArray();
			JSONArray jname2 = new JSONArray();
			JSONArray jvalue1 = new JSONArray();
			JSONArray jvalue2 = new JSONArray();
			JSONArray jvalue3 = new JSONArray();
			JSONArray jvalue4 = new JSONArray();
	    	JSONObject yearSpentCity = esYear.getJSONObject("yearSpentCity");
	    	JSONObject yearOrderCity = esYear.getJSONObject("yearOrderCity");
	    	JSONObject yearSpentSum = esYear.getJSONObject("yearSpentSum");
	    	JSONObject yearOrderSum = esYear.getJSONObject("yearOrderSum");
	    	//年消费金额城市内排名
	    	@SuppressWarnings("unchecked")
	    	Iterator<String> it = (Iterator <String>) yearSpentCity.keys();
	    	while(it.hasNext()){
	    		String key = it.next().toString();
	    		if(key.contains("_")){
	    			String[] keys = key.split("_");
	    			if(keys.length ==1){
	    				jname1.add(keys[0]);//时间
	    				jname2.add("--");//城市
	    			}else{
	    				jname1.add(keys[0]);//时间
	    				jname2.add(keys[1]);//城市
	    			}
	    			String value3 = yearSpentSum.get(keys[0]) == null ? "--" :yearSpentSum.getDouble(keys[0])+"";//年消费总金额
	    			String value4 = yearOrderSum.get(keys[0]) == null ? "--" : yearOrderSum.getInt(keys[0])+"";//年度订单数
	    			jvalue3.add(value3);
	    			jvalue4.add(value4);
	    		}
	    		Integer value1=yearSpentCity.get(key) == null ? null : yearSpentCity.getInt(key);//年消费金额城市内排名
	    		Integer value2=yearOrderCity.get(key) == null ? null :  yearOrderCity.getInt(key);//年订单数城市内排名
	    		jvalue1.add(value1);
	    		jvalue2.add(value2);
	    	}
	    	map.put("yearTime", jname1);//时间
	    	map.put("yearCity", jname2);//城市
	    	map.put("yearSpentCity", jvalue1);//年消费金额城市内排名
	    	map.put("yearOrderCity", jvalue2);//年订单数城市内排名
	    	map.put("yearSpentSum", jvalue3);//年消费总金额
	    	map.put("yearOrderSum", jvalue4);//年度订单数
	    }

	
	    //平台前五
	    JSONObject esTop = data.getJSONObject("esTop");
	    if(esTop !=null && !esTop.equals("")){
	        JSONArray jname3 = new JSONArray();
			JSONArray jvalue5 = new JSONArray();
			JSONArray jvalue6 = new JSONArray();
			JSONArray jvalue7 = new JSONArray();
	    	JSONObject avgSpent = esTop.getJSONObject("avgSpent");
	    	@SuppressWarnings("unchecked")
	    	Iterator<String> its = (Iterator<String>) avgSpent.keys();
	    	int i = 0;
	    	while(its.hasNext()){
	    		String key = its.next().toString();
	    		Double value5 = avgSpent.getDouble(key);
	    		Integer value6 = esTop.getJSONObject("avgOrderCount").getInt(key);
	    		Double value7 = esTop.getJSONObject("avgOrderSpent").getDouble(key);
	    		jname3.add(key);
	    		jvalue5.add(value5);
	    		jvalue6.add(value6);
	    		jvalue7.add(value7);
	    		i++;
	    		if(i>=3){
	    			break;
	    		}
	    	}
	    	map.put("avgName", jname3);//平台名
	    	map.put("avgSpent", jvalue5);//平台消费金额
	    	map.put("avgOrderCount", jvalue6);//平台订单数
	    	map.put("avgOrderSpent",jvalue7);//平台订单均额
	    }

	    //近一年消费分布

	    JSONObject esYs = data.getJSONObject("esYs");
	    if(esYs !=null && !esYs.equals("")){
		    JSONArray jname4 = new JSONArray();
			JSONArray jvalue8 = new JSONArray();
			JSONArray jvalue9 = new JSONArray();
			JSONArray jvalue10 = new JSONArray();
			JSONArray jvalue11 = new JSONArray();
			JSONArray jvalue12 = new JSONArray();
			JSONArray jvalue13 = new JSONArray();
	    	JSONObject monthSpent = esYs.getJSONObject("monthSpent");
	    	@SuppressWarnings("unchecked")
	    	Iterator<String> ites = (Iterator<String>) monthSpent.keys();
	    	while(ites.hasNext()){
	    		String key = ites.next().toString();
	    		jname4.add(key);
	    		Double value1 = monthSpent.getDouble(key);
	    		jvalue8.add(value1);
	    		if(esYs.getJSONObject("monthOrder") != null && !esYs.getJSONObject("monthOrder").equals("")){
	    			Integer value2 = esYs.getJSONObject("monthOrder").get(key) ==null ? null : esYs.getJSONObject("monthOrder").getInt(key);
		    		jvalue9.add(value2);
	    		}
	    		if(esYs.getJSONObject("monthSpentNight") != null && !esYs.getJSONObject("monthSpentNight").equals("")){
	    			Double value3 = esYs.getJSONObject("monthSpentNight").get(key) ==null ? null :esYs.getJSONObject("monthSpentNight").getDouble(key);
	    			jvalue10.add(value3);
	    		}
	    		if(esYs.getJSONObject("monthOrderNight") != null && !esYs.getJSONObject("monthOrderNight").equals("")){
	    			Integer value4 = esYs.getJSONObject("monthOrderNight").get(key) ==null ? null : esYs.getJSONObject("monthOrderNight").getInt(key);
		    		jvalue11.add(value4);
	    		}
	    		if(esYs.getJSONObject("msPercent") != null && !esYs.getJSONObject("msPercent").equals("")){
	    			Double value5 = esYs.getJSONObject("msPercent").get(key) ==null ? null : esYs.getJSONObject("msPercent").getDouble(key);
		    		jvalue12.add(value5);	
	    		}
	    		if(esYs.getJSONObject("moPercent") != null && !esYs.getJSONObject("moPercent").equals("")){
	    			Integer value6 = esYs.getJSONObject("moPercent").get(key) ==null ? null : esYs.getJSONObject("moPercent").getInt(key);
		    		jvalue13.add(value6);
	    		}
	    		
	    	}
	    	map.put("monthName", jname4);//月消费
	    	map.put("monthSpent", jvalue8);//月消费金额
	    	map.put("monthOrder", jvalue9);//月订单数
	    	map.put("monthSpentNight", jvalue10);//月内夜间消费总金额
	    	map.put("monthOrderNight", jvalue11);//月内夜间下单数
	    	map.put("msPercent", jvalue12);//月消费金额增长率
	    	map.put("moPercent", jvalue13);//月订单增长率
	    }

		return map;
	}
    //商品阶梯分布图
	public static Map<String,JSONArray> jsonTranMap(JSONObject obj){
		Map<String,JSONArray> map = new HashMap<String,JSONArray>();
		JSONObject goodPrice = obj.getJSONObject("data").getJSONObject("esTi").getJSONObject("goodPrice");
		if(goodPrice!=null && !goodPrice.equals("")){
			JSONArray jsonArr = new JSONArray();
			JSONArray jsonArrs = new JSONArray();
			JSONArray jsonArray = new JSONArray();
			JSONArray month = new JSONArray();
			Iterator it = (Iterator) goodPrice.keys();//月份
			
			while(it.hasNext()){
				String key = it.next().toString();
				month.add(key);
				JSONObject json = goodPrice.getJSONObject(key).getJSONObject("pi");//每月订单数和消费区间
				Iterator its = (Iterator) json.keys();
				while(its.hasNext()){
					String keys = its.next().toString();
					String values = json.getString(keys);
					jsonArr.add(key);
					jsonArr.add(keys);
					jsonArr.add(values);
					jsonArr.add(values);
					jsonArrs.add(jsonArr);
					jsonArr.clear();
				}
			}
			jsonArray.add(jsonArrs);
			map.put("goodPrice", jsonArray);
			map.put("goodPriceMonth", month);
		}
		return map;
	}
	public static Map<String,JSONObject> jsonMap(JSONObject obj){
		Map<String,JSONObject> map = new HashMap<String,JSONObject>();
		JSONObject data = obj.getJSONObject("data");
		JSONObject esTime =data.getJSONObject("esTime");//重要事件节点排序
		JSONObject esOy = data.getJSONObject("esOy");
		if(esTime.getString("minPayTime") !=null && !esTime.getString("minPayTime").equals("")){
			esTime.put("minPayTime", esTime.getString("minPayTime").substring(0,10));//最早支付时间
		}
		if(esTime.getString("maxPayTime") !=null && !esTime.getString("maxPayTime").equals("")){
			esTime.put("maxPayTime", esTime.getString("maxPayTime").substring(0,10));//最近支付时间
		}
		String[] maxNoPayMonth = null ;
		if( esTime.getString("maxNoPayMonth") != null && ! esTime.getString("maxNoPayMonth").equals("")){
			maxNoPayMonth =  esTime.getString("maxNoPayMonth").split(",");
			if(maxNoPayMonth != null && !maxNoPayMonth.equals("") ){
				esTime.put("maxNoPayMonth",maxNoPayMonth[1]);//最大无消费间隔月数
				esTime.put("maxNoPayMonthRegion",maxNoPayMonth[0]);//最大无消费间隔月数	
			}		
		}else{
			esTime.put("maxNoPayMonth","");//最大无消费间隔月数
			esTime.put("maxNoPayMonthRegion","");//最大无消费间隔月数	
		}
			
		JSONObject esTimeJson = new JSONObject();
		JSONArray esTimeArr = new JSONArray();
		StringBuilder builder = new StringBuilder();
		if( esTime.getString("maxPayMonth") !=null && !esTime.getString("maxPayMonth").equals(esTime.getString("maxOrderMonth"))){//对月份进行排名，放到jsonArray中显示
			builder.append(maxNoPayMonth[1]).append(":最大无消费间隔月数").append(maxNoPayMonth[0]).append("个月#").append(esTime.getString("maxPayMonth"));
			builder.append(":最高消费金额月份#").append(esTime.getString("maxOrderMonth")).append(":最多订单月份");
			String br = builder.toString();
			String[] strArr = br.split("#");
			List list = Arrays.asList(strArr);
			Collections.sort(list);
		    esTimeArr = JSONArray.fromObject(list);
		    esTimeJson.put("estTime3", esTimeArr);
		}else if(maxNoPayMonth != null && !maxNoPayMonth.equals("")) {
			builder.append(maxNoPayMonth[1]).append(":最大无消费间隔月数").append(maxNoPayMonth[0]).append("个月#").append(esTime.getString("maxPayMonth"));
			builder.append(":最高消费金额月份<br/>最多订单月份");
			String br = builder.toString();
			String[] strArr = br.split("#");
			List list = Arrays.asList(strArr);
			Collections.sort(list);
		    esTimeArr = JSONArray.fromObject(list);
		    esTimeJson.put("estTime3", esTimeArr);
		}else{
			builder.append("--:最大无消费间隔月数").append("--个月#").append("--");
			builder.append(":最高消费金额月份<br/>最多订单月份");
			String br = builder.toString();
			String[] strArr = br.split("#");
			List list = Arrays.asList(strArr);
		    esTimeArr = JSONArray.fromObject(list);
		    esTimeJson.put("estTime3", esTimeArr);
		}
		map.put("esTime",esTime);
		//年订单行为统计
		esOy.element("ocYear", (esOy.get("ocYear")==null ||esOy.get("ocYear").equals("")||esOy.get("ocYear").equals("null") ||esOy.get("ocYear").equals("\"null\""))?"--": esOy.get("ocYear"));
		esOy.element("roCount", (esOy.get("roCount")==null || esOy.get("roCount").equals("") ||esOy.get("roCount").equals("null")||esOy.get("roCount").equals("\"null\""))?"--":esOy.get("roCount"));
		esOy.element("pcYear",(esOy.get("pcYear")==null ||esOy.get("pcYear").equals("")||esOy.get("pcYear").equals("null") ||esOy.get("pcYear").equals("\"null\""))?"--": esOy.get("pcYear"));
		esOy.element("roPercent", (esOy.get("roPercent")==null ||esOy.get("roPercent").equals("")||esOy.get("roPercent").equals("null") ||esOy.get("roPercent").equals("\"null\""))?"--": esOy.get("roPercent"));
		esOy.element("pcPercent", (esOy.get("pcPercent")==null ||esOy.get("pcPercent").equals("")||esOy.get("pcPercent").equals("null")||esOy.get("pcPercent").equals("\"null\""))?"--": esOy.get("pcPercent"));
		map.put("esOy",esOy );//年订单行为统计
		map.put("esTimeJson", esTimeJson);//最大无消费间隔月数,最高消费金额月份,最多订单月份--排序
		return map;
		
	}
	public static void main(String[] args) {
	JSONArray arr = new JSONArray();
	   arr.add(1);
	   arr.add(2);
	   arr.add(3);
	   arr.add(4);
	   arr.add(5);
	   arr.add(6);
	   Map map = new HashMap();
	   map.put("arr",arr);
	   arr.clear();
	   System.out.println(map.get("arr").toString());
	   List list = new ArrayList();
	   list.add(1);
	   list.add(2);
	   list.add(3);
	   list.add(4);
	   list.add(5);
	   map.put("list", list);
	   list.clear();
	   System.out.println(map.get("list"));
	
	}

}
