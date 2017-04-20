package com.ccx.credit.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author Administrator
 *
 */
public class FormatUtil {
	// 除法运算默认精度
	public static final int DEF_DIV_SCALE = 10;

	/** 
	* 提供精确的加法运算。 
	* 
	* @param v1 被加数 
	* @param v2 加数 
	* @return 两个参数的和 
	*/ 
	public static double add(double v1, double v2) { 
	   BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
	   BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
	   return b1.add(b2).doubleValue(); 
	} 
	
	/** 
	* 提供精确的减法运算。 
	* 
	* @param v1 被减数 
	* @param v2 减数 
	* @return 两个参数的差 
	*/ 
	public static double sub(double v1, double v2) { 
	   BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
	   BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
	   return b1.subtract(b2).doubleValue(); 
	} 
	
	/** 
	* 提供精确的乘法运算。 
	* 
	* @param v1 被乘数 
	* @param v2 乘数 
	* @return 两个参数的积 
	*/ 
	public static double mul(double v1, double v2) { 
	   BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
	   BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
	   return b1.multiply(b2).doubleValue(); 
	} 
	
	/** 
	* 提供（相对）精确的除法运算，当发生除不尽的情况时， 
	* 精确到小数点以后10位，以后的数字四舍五入。 
	* 
	* @param v1 被除数 
	* @param v2 除数 
	* @return 两个参数的商 
	*/ 
	public static double div(double v1, double v2) { 
	   return div(v1, v2, DEF_DIV_SCALE); 
	} 
	/** 
	* 提供（相对）精确的除法运算。 
	* 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。 
	* 
	* @param v1 被除数 
	* @param v2 除数 
	* @param scale 表示表示需要精确到小数点以后几位。 
	* @return 两个参数的商 
	*/ 
	public static double div(double v1, double v2, int scale) { 
	   if (scale < 0) { 
	    throw new IllegalArgumentException("The scale must be a positive integer or zero"); 
	   } 
	   BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
	   BigDecimal b2 = new BigDecimal(Double.toString(v2)); 
	   return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	} 
	
	/** 
	* 提供精确的小数位四舍五入处理。 
	* 
	* @param v 需要四舍五入的数字 
	* @param scale 小数点后保留几位 
	* @return 四舍五入后的结果 
	*/ 
	public static double round(double v, int scale) { 
	   if (scale < 0) { 
	    throw new IllegalArgumentException("The scale must be a positive integer or zero"); 
	   } 
	   if(Double.isNaN(v)){return 0.0;}
	   BigDecimal b = new BigDecimal(Double.toString(v)); 
	   BigDecimal one = new BigDecimal("1"); 
	   return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	} 

	/**
	 * 格式化货币显示，货币显示默认会带个前缀，如果英文操作系统，则会显示类似 [$2,454,534.23]，而中文则显示[￥2,454,534.23]，<br>
	 * 此类中用以下规则来处理相应处理：<br>
	 * 第一，可以选择地域，即有Locale这个参数对象;<br>
	 * 第二，可以选择是否加前缀;<br>
	 * 第三，如果选择不加前缀，则自动将Locale高为US;<br>
	 * 第四，如果Locale为空，则默认为中国人民币;<br> 
	 * (注：Locale.TRADITIONAL_CHINESE或者Locale.TAIWAN应该为台湾，而不要想当然认为Locale.CHINESE为台湾)
	 */
	public static String moneyFormat(Number v , Locale area , boolean hasPrefix) {
		Locale local = area == null ? Locale.US : area;
		if (!hasPrefix) local = Locale.US;
		NumberFormat formator = NumberFormat.getCurrencyInstance(local);
		DecimalFormat formatorNoPrefix = new DecimalFormat("#,##0.###");
		String money = formator.format(v);
		String tag = money.substring(0, 1);
		String r = formatorNoPrefix.format(v);
		if (hasPrefix) r = tag + r;
		return r;
	}
	
	public static String moneyFormat(Number v , boolean hasPrefix) {
		return moneyFormat(v , null , hasPrefix);
	}
	
	public static String moneyFormat(Number v) {
		return moneyFormat(v , null , false);
	}
	
	public static String moneyFormat(double v , Locale area) {
		return moneyFormat(v , area , true);
	}
	
	//不能保留对应的模式
	public static double doubleFormat(double value,String pattern ){
		
		if(String.valueOf(value).equals("NaN")){
			return 0.0;
		}
        return  Double.parseDouble(new DecimalFormat(pattern).format(value));   
	}
	
	/**
	 * 按照pattern指定格式显示数据，四舍五入 保留的位数
	 */
	public static String doubleFormat(double value,String pattern, int scale){
		value = round(value,scale);
        return new DecimalFormat(pattern).format(value);
	}
	
	/**
	 * 按照pattern指定格式显示数据，没有进行四舍五入
	 */
	public static String doubleFormatForString(double value,String pattern ){
        return  new DecimalFormat(pattern).format(value);
	}
	
	/**
	 * 显示数字，非科学计数法
	 * @param value
	 * @return
	 */
	public static String showNumber(double value){   
        return  new BigDecimal(Double.toString(value)).toString();   
	}
	
	public static String formatToPattern(double value, String pattern){
		  DecimalFormat formatter = new DecimalFormat();
		  formatter.applyPattern(pattern);
		  return formatter.format(value);

	}
	
	/** 
	* 提供取反的操作（刘芳）。 
	* 
	* @param 操作数  value
	* @return 操作数的相反数 
	*/ 
	public static double negtive(double value) { 
	   BigDecimal b1 = new BigDecimal(Double.toString(value)); 
	   return b1.negate().doubleValue(); 
	} 
	
	/**
	 * 经济指标 单位——“元” 格式化
	 * 
	 * @author 张鹏凯
	 * @since 2010-9-6 下午02:18:51
	 * @param value 
	 * @return
	 */
	public static String doubleResultFormat(Double value) {
		if(value==null)
			return "";
		String result = FormatUtil.doubleFormat(value, "#,##0.00", 2);
		return result; 
	}
	
	/**
	 * 收益率、利差 结果格式化
	 * 
	 * @author 吴玉锋
	 * @since 2011-06-29 下午02:18:51
	 * @param value 
	 * @return
	 */
	public static String double4ResultFormat(Double value) {
		if(value==null)
			return "";
		String result = FormatUtil.doubleFormat(value, "#,##0.0000", 4);
		return result; 
	}
	
	/**
	 * 百分比(%)结果格式化
	 * 
	 * @author 张鹏凯
	 * @since 2010-9-6 下午02:23:17
	 * @param value
	 * @return
	 */
	public static String percentResultFormat(Double value) {
		if(value==null)
			return "";
		String result = FormatUtil.doubleFormat(value, "###0.00", 2);
		return result; 
	}
	
	/**
	 * 倍数(X) 结果格式化
	 * 
	 * @author 张鹏凯
	 * @since 2010-9-6 下午04:19:04
	 * @param value
	 * @return
	 */
	public static String multipleResultFormat(Double value) {
		if(value==null)
			return "";
		String result = FormatUtil.doubleFormat(value, "###0.00", 2);
		return result; 
	}
}
