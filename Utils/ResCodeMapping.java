package com.ccx.credit.util;

import java.util.HashMap;
import java.util.Map;

public class ResCodeMapping {
	
	public final static Map<String, String> RESCODE_RESMSG_MAP = new HashMap<String, String>();  
    
    static {  
    	
    	
    	RESCODE_RESMSG_MAP.put("0000", "处理成功");
    	
    	RESCODE_RESMSG_MAP.put("1001", "账号不存在或被禁用"); 
    	RESCODE_RESMSG_MAP.put("1002", "账户余额不足"); 
    	RESCODE_RESMSG_MAP.put("1003", "账户没有此接口访问权限"); 
        
    	RESCODE_RESMSG_MAP.put("1004", "账户请求已超限"); 
        
    	RESCODE_RESMSG_MAP.put("1011", "请求的资源不存在"); 
    	RESCODE_RESMSG_MAP.put("1012", "参数为空或格式错误"); 
    	RESCODE_RESMSG_MAP.put("1013", "验签失败"); 
        
    	RESCODE_RESMSG_MAP.put("2001", "没有查询到结果");
    	RESCODE_RESMSG_MAP.put("2002","信息不足,不予评分");
        
    	RESCODE_RESMSG_MAP.put("2010", "身份认证成功");
    	RESCODE_RESMSG_MAP.put("2011", "姓名，身份证不一致");
    	RESCODE_RESMSG_MAP.put("2012", "库中无身份证数据");
    	RESCODE_RESMSG_MAP.put("2013", "条件不符合要求");
        
    	RESCODE_RESMSG_MAP.put("2021", "姓名，身份证，银行卡号，手机号不一致");
    	RESCODE_RESMSG_MAP.put("2022", "银行卡号，手机号不一致导致短信发送失败");
    	RESCODE_RESMSG_MAP.put("2023", "银行卡号，手机号验证失败");
    	RESCODE_RESMSG_MAP.put("2024", "其他验证错误");

    	RESCODE_RESMSG_MAP.put("2030", "验证成功");
    	RESCODE_RESMSG_MAP.put("2031", "验证失败");
    	RESCODE_RESMSG_MAP.put("2032", "数据异常");
        
    	RESCODE_RESMSG_MAP.put("2041", "短信发送失败");
        
    	RESCODE_RESMSG_MAP.put("2060", "匹配成功");
    	RESCODE_RESMSG_MAP.put("2061", "匹配失败");
    	RESCODE_RESMSG_MAP.put("2062", "号码不存在");

    	RESCODE_RESMSG_MAP.put("3011", "微信用户未注册");
    	RESCODE_RESMSG_MAP.put("3012", "微信用户已注册，未验证身份");
    	RESCODE_RESMSG_MAP.put("3013", "微信用户已注册，未短信验证");
        
    	RESCODE_RESMSG_MAP.put("3021", "获取微信Openid失败");
        
    	RESCODE_RESMSG_MAP.put("3031", "短信验证码发送失败");
    	RESCODE_RESMSG_MAP.put("3032", "短信验证码当天发送次数已超限");
    	RESCODE_RESMSG_MAP.put("3033", "短信验证码已过期");
    	RESCODE_RESMSG_MAP.put("3034", "身份验证手机号码与短信验证码发送的手机号码不一致");
    	RESCODE_RESMSG_MAP.put("3035", "短信验证码不一致");
    	RESCODE_RESMSG_MAP.put("3036", "还未申请发送短信验证码");
        
    	RESCODE_RESMSG_MAP.put("3041", "身份认证次数已超限");
        
    	RESCODE_RESMSG_MAP.put("4001","提交失败");
        
    	RESCODE_RESMSG_MAP.put("9000", "账户预检查通过");
        
    	RESCODE_RESMSG_MAP.put("9999", "系统错误");

    } 
    
    public static String resCodeToMsg(String resCode){
    	if(resCode!=null){
    		return RESCODE_RESMSG_MAP.get(resCode);
    	}else{
    		return "";
    	}
    }
    

}
