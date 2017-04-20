package com.ccx.credit.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnCode {
    private static Map<String,String> returnCodeMap = new HashMap<String,String>();
    static {
    	returnCodeMap.put("0000", "处理成功");
    	returnCodeMap.put("1001", "账号不存在或被禁用");
    	returnCodeMap.put("1002", "账户余额不足");
    	returnCodeMap.put("1003", "账户没有此接口的访问权限");
    	returnCodeMap.put("1011", "请求的资源不存在");
    	returnCodeMap.put("1012", "参数为空或格式错误");
    	returnCodeMap.put("1013", "验签失败");
    	returnCodeMap.put("2001", "没有找到查询结果");
    	returnCodeMap.put("2010", "身份认证成功");
    	returnCodeMap.put("2011", "姓名-身份证不一致");
    	returnCodeMap.put("2012", "库中无身份证数据");
    	returnCodeMap.put("9999", "系统错误");
    }
    
    public static String getMsg(String key){
    	String msg = null;
    	if ( returnCodeMap.containsKey(key) ) {
    		msg = returnCodeMap.get(key);
    	}
    	return msg;
    }
    
    public static Map<String,String> getReturnCodeMap(){
	   return returnCodeMap;
    }
}
