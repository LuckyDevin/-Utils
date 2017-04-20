package com.ccx.credit.util;

import java.util.HashMap;
import java.util.Map;

public class ResCodeMap {
	public final static Map<String, String> RESCODE_MSG_MAP = new HashMap<String, String>(); 
	static {  
		RESCODE_MSG_MAP.put("0000", "处理成功");
		RESCODE_MSG_MAP.put("0001", "未查到结果");
		RESCODE_MSG_MAP.put("1001", "账号不存在或被禁用"); 
		RESCODE_MSG_MAP.put("1002", "账户余额不足"); 
		RESCODE_MSG_MAP.put("1003", "账户没有此接口访问权限"); 
		    
		RESCODE_MSG_MAP.put("1004", "账户请求已超限"); 
		    
		RESCODE_MSG_MAP.put("1011", "请求的资源不存在"); 
		RESCODE_MSG_MAP.put("1012", "参数为空或格式错误"); 
		RESCODE_MSG_MAP.put("1013", "验签失败"); 
		    
		RESCODE_MSG_MAP.put("1021","没有查询到结果");
		RESCODE_MSG_MAP.put("1022","请求数据不存在或已失效");
		    
		RESCODE_MSG_MAP.put("2001", "没有查询到结果");
		RESCODE_MSG_MAP.put("2002","信息不足,不予评分");
		RESCODE_MSG_MAP.put("2003","历史订单中不包含此查询主体信息");
		RESCODE_MSG_MAP.put("2004","查询主体信息不全，无法查询");
		    
		RESCODE_MSG_MAP.put("2005","企业名称没查过工商企业查询接口");
		RESCODE_MSG_MAP.put("2006","自然人没在工商企业查询接口返回列表中或者职务不对应");
		    
		RESCODE_MSG_MAP.put("2007", "访问次数超过指定次数");
		    
		RESCODE_MSG_MAP.put("2008", "前置条件校验失败");
		    
		    
		RESCODE_MSG_MAP.put("2010", "身份验证成功");
		RESCODE_MSG_MAP.put("2011", "身份验证失败");
		RESCODE_MSG_MAP.put("2012", "库中无记录");
		RESCODE_MSG_MAP.put("2013", "输入信息不符合要求");
		    
		RESCODE_MSG_MAP.put("2021", "姓名，身份证，银行卡号，手机号不一致");
		RESCODE_MSG_MAP.put("2022", "银行卡号，手机号不一致导致短信发送失败");
		RESCODE_MSG_MAP.put("2023", "银行卡号，手机号验证失败");
		RESCODE_MSG_MAP.put("2024", "其他验证错误");
		RESCODE_MSG_MAP.put("2025","银行卡-姓名不一致");

		RESCODE_MSG_MAP.put("2030", "验证成功");
		RESCODE_MSG_MAP.put("2031", "验证失败");
		RESCODE_MSG_MAP.put("2032", "数据异常");
		RESCODE_MSG_MAP.put("2033", "特殊验证成功");
		    
		RESCODE_MSG_MAP.put("2037", "该数据验证失败次数超过限制");
		RESCODE_MSG_MAP.put("2038", "数据格式错误");
		RESCODE_MSG_MAP.put("2039", "暂不支持该数据验证");
		    
		RESCODE_MSG_MAP.put("2041", "短信发送失败");
		    
		RESCODE_MSG_MAP.put("2051","没有乘机记录");
		    
		RESCODE_MSG_MAP.put("2060", "匹配成功");
		RESCODE_MSG_MAP.put("2061", "匹配失败");
		RESCODE_MSG_MAP.put("2062", "号码不存在");
		RESCODE_MSG_MAP.put("2063","无姓名或身份证信息");

		RESCODE_MSG_MAP.put("3011", "微信用户未注册");
		RESCODE_MSG_MAP.put("3012", "微信用户已注册，未验证身份");
		RESCODE_MSG_MAP.put("3013", "微信用户已注册，未短信验证");
		    
		RESCODE_MSG_MAP.put("3021", "获取微信Openid失败");
		    
		RESCODE_MSG_MAP.put("3031", "短信验证码发送失败");
		RESCODE_MSG_MAP.put("3032", "短信验证码当天发送次数已超限");
		RESCODE_MSG_MAP.put("3033", "短信验证码已过期");
		RESCODE_MSG_MAP.put("3034", "身份验证手机号码与短信验证码发送的手机号码不一致");
		RESCODE_MSG_MAP.put("3035", "短信验证码不一致");
		RESCODE_MSG_MAP.put("3036", "还未申请发送短信验证码");
		    
		RESCODE_MSG_MAP.put("3041", "身份认证次数已超限");
		    
		RESCODE_MSG_MAP.put("3051","邮箱验证码一致");
		RESCODE_MSG_MAP.put("3052","邮箱验证码不一致");
		RESCODE_MSG_MAP.put("3053","邮箱验证码超时");
		RESCODE_MSG_MAP.put("3054","邮箱验证码发送失败");
		    
		RESCODE_MSG_MAP.put("3060","绑定卡数目超出上限");
		RESCODE_MSG_MAP.put("3061","卡号信息已存在");
		    
		RESCODE_MSG_MAP.put("4001","提交失败");
		    
		RESCODE_MSG_MAP.put("4010", "学历信息一致");
		RESCODE_MSG_MAP.put("4011", "学历信息不一致");
		RESCODE_MSG_MAP.put("4012", "库中信息不完整");
		RESCODE_MSG_MAP.put("4100", "查询成功");
		
		//个人报告批量查询详情（导出excel）转report_flag (0，-1，1)时特用
		RESCODE_MSG_MAP.put("666", "查询失败");
		RESCODE_MSG_MAP.put("777", "正在查询");
		    
		RESCODE_MSG_MAP.put("7001", "验证码错误"); 
		RESCODE_MSG_MAP.put("7002", "数据源限制导致查询失败"); 
		RESCODE_MSG_MAP.put("7003", "数据源维护中或服务暂停"); 
		    
		RESCODE_MSG_MAP.put("9000", "账户预检查通过");
		    
		RESCODE_MSG_MAP.put("9100","系统数据错误");
		    
		RESCODE_MSG_MAP.put("9901", "系统繁忙，请稍后再试");
		RESCODE_MSG_MAP.put("9902", "系统维护中，请稍后再试");
		RESCODE_MSG_MAP.put("9999", "系统错误");
	}
	 public static String resCodeToMsg(String resCode){
	    	if(resCode!=null){
	    		return RESCODE_MSG_MAP.get(resCode);
	    	}else{
	    		return "";
	    	}
	    }   
	    
}
