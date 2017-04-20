package com.ccx.credit.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ccx.credit.model.HistoryResultBean;

public class FinancialUtils {
	
	public static JSONObject financialSetInfo(String jsonStr){
		if(jsonStr != null && !jsonStr.equals("")){
			JSONObject json = JSONObject.parseObject(jsonStr);
			if(json.getString("resCode").equals("0000")){
				JSONObject data = json.getJSONObject("data");
				JSONObject info = new JSONObject();
				if(data.getString("info1") != null){
					JSONObject info1 = data.getJSONObject("info1");
					JSONObject obj = new JSONObject();
					obj.put("chargeOffSum" , financialMoney(info1.getString("chargeOffSum")));
					obj.put("chargeOffCount",info1.getString("chargeOffCount"));
					obj.put("payOfSum", financialMoney(info1.getString("payOfSum")));
					obj.put("inVestSum", financialMoney(info1.getString("inVestSum")));
					obj.put("postSum", financialMoney(info1.getString("postSum")));
					obj.put("postCount", info1.getString("postCount"));
					obj.put("olPayOfSum", financialMoney(info1.getString("olPayOfSum")));
					obj.put("cardCount", info1.getString("cardCount"));
					obj.put("cardRemaSum", financialMoney(info1.getString("cardRemaSum")));
					obj.put("cardAge", info1.getString("cardAge"));
					info.put("info1", obj);
				}else{
					info.put("info1", null);
				}
				if(data.getString("info2") != null){
					JSONObject info2 = data.getJSONObject("info2");
					JSONObject obj = new JSONObject();
					obj.put("chargeOffSum" , financialMoney(info2.getString("chargeOffSum")));
					obj.put("chargeOffCount",info2.getString("chargeOffCount"));
					obj.put("payOfSum", financialMoney(info2.getString("payOfSum")));
					obj.put("inVestSum", financialMoney(info2.getString("inVestSum")));
					obj.put("postSum", financialMoney(info2.getString("postSum")));
					obj.put("postCount", info2.getString("postCount"));
					obj.put("olPayOfSum", financialMoney(info2.getString("olPayOfSum")));
					obj.put("cardRemaSum", financialMoney(info2.getString("cardRemaSum")));
					obj.put("rateOfIncrease", info2.getString("rateOfIncrease"));
					obj.put("postLevel",info2.getString("postLevel"));
					info.put("info2", obj);
				}else{
					info.put("info2", null);
				}
				if(data.getString("info3") != null){
					JSONObject info3 = data.getJSONObject("info3");
					JSONObject obj = new JSONObject();
					obj.put("payOfMoney" , financialMoney(info3.getString("payOfMoney")));
					obj.put("payOfCount",info3.getString("payOfCount"));
					obj.put("refundMoney", financialMoney(info3.getString("refundMoney")));
					obj.put("currentBalance", financialMoney(info3.getString("currentBalance")));
					obj.put("payOfMoneyTwelve", financialMoney(info3.getString("payOfMoneyTwelve")));
					obj.put("payOfCountTwelve", info3.getString("payOfCountTwelve"));
					obj.put("refundMoneyTwelve", financialMoney(info3.getString("refundMoneyTwelve")));
					obj.put("currentBalanceTwelve", financialMoney(info3.getString("currentBalanceTwelve")));
					obj.put("paySumMoney", financialMoney(info3.getString("paySumMoney")));
					obj.put("blueCardCount", info3.getString("blueCardCount"));
					obj.put("maxLevel", cardRank(info3.getString("maxLevel")));
					obj.put("longestNumber", info3.getString("longestNumber"));
					obj.put("blueCardAge",info3.getString("blueCardAge"));
					info.put("info3", obj);
				}else{
					info.put("info3", null);
				}
				if(data.getString("info4") != null){
					JSONObject info4 = data.getJSONObject("info4");
					JSONObject obj = new JSONObject();
					obj.put("bankNumber" , info4.getString("bankNumber"));
					obj.put("debitCardNumber",info4.getString("debitCardNumber"));
					obj.put("blueCardNumber",info4.getString("blueCardNumber"));
					obj.put("blueCardMaxLevel",cardRank(info4.getString("blueCardMaxLevel")));
					obj.put("debitCardBalance", financialMoney(info4.getString("debitCardBalance")));
					obj.put("loanFundMoney", financialMoney(info4.getString("loanFundMoney")));
					obj.put("loanFundCount", info.getString("loanFundCount"));
					obj.put("jdSumMoney", financialMoney(info4.getString("jdSumMoney")));
					obj.put("nLoanFundMoney", financialMoney(info4.getString("nLoanFundMoney")));
					obj.put("nLoanFundCount", info4.getString("nLoanFundCount"));
					info.put("info4", obj);
				}else{
					info.put("info4", null);
				}
				return info;
			}
		}
		
		return null;
		
	}
	public static JSONObject finDataProcess(List<HistoryResultBean> list){
		JSONObject info = new JSONObject();
		if(list != null && list.size()>0){
			for(int i =0 ; i<list.size();i++){
				HistoryResultBean bean = list.get(i);
				if(bean.getResult().equals("0000")){
					if(bean.getType().equals("241")){
						JSONObject info1 = JSONObject.parseObject(bean.getHistory_data()).getJSONObject("data");
						JSONObject obj = new JSONObject();
						obj.put("chargeOffSum" , financialMoney(info1.getString("chargeOffSum")));
						obj.put("chargeOffCount",info1.getString("chargeOffCount"));
						obj.put("payOfSum", financialMoney(info1.getString("payOfSum")));
						obj.put("inVestSum", financialMoney(info1.getString("inVestSum")));
						obj.put("postSum", financialMoney(info1.getString("postSum")));
						obj.put("postCount", info1.getString("postCount"));
						obj.put("olPayOfSum", financialMoney(info1.getString("olPayOfSum")));
						obj.put("cardCount", info1.getString("cardCount"));
						obj.put("cardRemaSum", financialMoney(info1.getString("cardRemaSum")));
						obj.put("cardAge", info1.getString("cardAge"));
						info.put("info1", obj);
					}
                     if(bean.getType().equals("242")){
						JSONObject info2 = JSONObject.parseObject(bean.getHistory_data()).getJSONObject("data");
						JSONObject obj = new JSONObject();
						obj.put("chargeOffSum" , financialMoney(info2.getString("chargeOffSum")));
						obj.put("chargeOffCount",info2.getString("chargeOffCount"));
						obj.put("payOfSum", financialMoney(info2.getString("payOfSum")));
						obj.put("inVestSum", financialMoney(info2.getString("inVestSum")));
						obj.put("postSum", financialMoney(info2.getString("postSum")));
						obj.put("postCount", info2.getString("postCount"));
						obj.put("olPayOfSum", financialMoney(info2.getString("olPayOfSum")));
						obj.put("cardRemaSum", financialMoney(info2.getString("cardRemaSum")));
						obj.put("rateOfIncrease", info2.getString("rateOfIncrease"));
						obj.put("postLevel",info2.getString("postLevel"));
						info.put("info2", obj);
					}
					if(bean.getType().equals("243")){
						JSONObject info3 = JSONObject.parseObject(bean.getHistory_data()).getJSONObject("data");
						JSONObject obj = new JSONObject();
						obj.put("payOfMoney" , financialMoney(info3.getString("payOfMoney")));
						obj.put("payOfCount",info3.getString("payOfCount"));
						obj.put("refundMoney", financialMoney(info3.getString("refundMoney")));
						obj.put("currentBalance", financialMoney(info3.getString("currentBalance")));
						obj.put("payOfMoneyTwelve", financialMoney(info3.getString("payOfMoneyTwelve")));
						obj.put("payOfCountTwelve", info3.getString("payOfCountTwelve"));
						obj.put("refundMoneyTwelve", financialMoney(info3.getString("refundMoneyTwelve")));
						obj.put("currentBalanceTwelve", financialMoney(info3.getString("currentBalanceTwelve")));
						obj.put("paySumMoney", financialMoney(info3.getString("paySumMoney")));
						obj.put("blueCardCount", info3.getString("blueCardCount"));
						obj.put("maxLevel", cardRank(info3.getString("maxLevel")));
						obj.put("longestNumber", info3.getString("longestNumber"));
						obj.put("blueCardAge",info3.getString("blueCardAge"));
						info.put("info3", obj);
					}
					if(bean.getType().equals("244")){
						JSONObject info4 = JSONObject.parseObject(bean.getHistory_data()).getJSONObject("data");
						JSONObject obj = new JSONObject();
						obj.put("bankNumber" , info4.getString("bankNumber"));
						obj.put("debitCardNumber",info4.getString("debitCardNumber"));
						obj.put("blueCardNumber",info4.getString("blueCardNumber"));
						obj.put("blueCardMaxLevel",cardRank(info4.getString("blueCardMaxLevel")));
						obj.put("debitCardBalance", financialMoney(info4.getString("debitCardBalance")));
						obj.put("loanFundMoney", financialMoney(info4.getString("loanFundMoney")));
						obj.put("loanFundCount", info4.getString("loanFundCount"));
						obj.put("jdSumMoney", financialMoney(info4.getString("jdSumMoney")));
						obj.put("nLoanFundMoney", financialMoney(info4.getString("nLoanFundMoney")));
						obj.put("nLoanFundCount", info4.getString("nLoanFundCount"));
						info.put("info4", obj);
					}
				}
			}
		}
		
		return info;
	}
	
	
   public static String financialMoney(String str){
	 if(str!=null && !str.equals("")){
		 int numstr = Integer.valueOf(str);
		 switch(numstr){
		 case 0:
			 return "0.00~1,000.00";
		 case 1:
			 return "1,000.00~2,000.00 ";
		 case 2:
			 return "2,000.00~3,000.00";
		 case 3:
			 return "3,000.00~4,000.00 ";
		 case 4:
			 return "4,000.00~5,000.00";
		 case 5:
			 return "5,000.00~6,000.00";
		 case 6:
			 return "6,000.00~7,000.00";
		 case 7:
			 return "7,000.00~8,000.00";
		 case 8:
			 return "8,000.00~9,000.00";
		 case 9:
			 return "9,000.00~10,000.00";
		 case 10:
			 return "10,000.00~20,000.00";
		 case 11:
			 return "20,000.00~30,000.00";
		 case 12:
			 return "30,000.00~40,000.00";
		 case 13:
			 return "40,000.00~50,000.00";
		 case 14:
			 return "50,000.00~60,000.00";
		 case 15:
			 return "60,000.00~70,000.00";
		 case 16:
			 return "70,000.00~80,000.00";
		 case 17:
			 return "80,000.00~90,000.00";
		 case 18:
			 return "90,000.00~100,000.00";
		 case 19:
			 return "100,000.00~200,000.00";
		 case 20:
			 return "200,000.00~300,000.00";
		 case 21:
			 return "300,000.00~400,000.00";
		 case 22:
			 return "400,000.00~500,000.00";
		 case 23:
			 return "500,000.00~600,000.00";
		 case 24:
			 return "600,000.00~700,000.00";
		 case 25:
			 return "700,000.00~800,000.00";
		 case 26:
			 return "800,000.00~900,000.00";
		 case 27:
			 return "900,000.00~1,000,000.00";
		 case 28:
			 return "1,000,000.00~2,000,000.00";
		 case 29:
			 return "2,000,000.00~3,000,000.00";
		 case 30:
			 return "3,000,000.00~4,000,000.00";
		 case 31:
			 return "4,000,000.00~5,000,000.00";
		 case 32:
			 return "5,000,000.00~6,000,000.00";
		 case 33:
			 return "6,000,000.00~7,000,000.00";
		 case 34:
			 return "7,000,000.00~8,000,000.00";
		 case 35:
			 return "8,000,000.00~9,000,000.00";
		 case 36:
			 return "9,000,000.00~10,000,000.00";
		 case 37:
			 return "10,000,000.00以上";
		 case 99:
			 return "未知";	 
		 }
	 }
	 return str;
   }
   public static String financialAge(String str){
	   if(str !=null && !"".equals(str)){
		   Float  cardAge = Float.valueOf(str);
		   if(cardAge > 0 && cardAge <= 1 ){
			   return "0";
		   }else  if(cardAge > 1 && cardAge < 6 ){
			   return "0.5";
		   }else  if(cardAge >= 6 && cardAge < 12 ){
			   return "1";
		   }else  if(cardAge >= 12 && cardAge < 18 ){
			   return "1.5";
		   }else  if(cardAge >= 18 && cardAge < 24 ){
			   return "2";
		   }else  if(cardAge >= 24 && cardAge < 30 ){
			   return "2.5";
		   }else  if(cardAge >= 30 && cardAge < 36 ){
			   return "3";
		   }else  if(cardAge >= 36 && cardAge < 42 ){
			   return "3.5";
		   }else  if(cardAge >= 42 && cardAge < 48 ){
			   return "4";
		   }else  if(cardAge >= 48 && cardAge < 54){
			   return "4.5";
		   }else  if(cardAge >= 54 && cardAge < 60 ){
			   return "5";
		   }else  if(cardAge >= 60 && cardAge < 72 ){
			   return "5.5";
		   }else  if(cardAge >= 72 && cardAge < 84 ){
			   return "7";
		   }else  if(cardAge >= 84&& cardAge < 96){
			   return "8";
		   }else  if(cardAge >= 96 && cardAge < 108){
			   return "9";
		   }else  if(cardAge >= 108 && cardAge < 120 ){
			   return "10";
		   }else  if(cardAge >120 ){
			   return "11";
		   }   
	   }
       return str;
   }
   public static String cardRank(String str){
	   if(str != null && !str.equals("")){
		   switch (str){
		      case "0":
			   return"未知";
		      case "01":
		          return "普卡";
		      case "02":
		    	  return "金卡";  
		      case "03":
		    	  return "白金卡";
		      case "04":
		    	  return "钻石卡";
		      case "05":
		    	  return "VIP卡";
		   }
	   }
	return str;
	   
   }
}





































